package mediseen.pilltracker;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

public class ShoppingListAdapter extends RecyclerView
        .Adapter<ShoppingListAdapter
        .ViewHolder> {
    private ArrayList<String> mDataset;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextViewPlus pillName;
        public ImageButton exitButton;
        public ImageView circleLogo;
        public ViewHolder(View itemView) {
            super(itemView);
            pillName = (TextViewPlus) itemView.findViewById(R.id.pillName);
            exitButton = (ImageButton) itemView.findViewById(R.id.exitButton);
            circleLogo = (ImageView) itemView.findViewById(R.id.circleLogo);
        }
    }

    public ShoppingListAdapter(Context context, ArrayList<String> myDataset) {
        this.context = context;
        mDataset = myDataset;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_shopping_list, parent, false);
        ViewHolder dataObjectHolder = new ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holderT, final int position) {
        final ViewHolder holder = holderT;

        holder.pillName.setText(mDataset.get(position));
        holder.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset.remove(position);
                notifyItemRemoved(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}