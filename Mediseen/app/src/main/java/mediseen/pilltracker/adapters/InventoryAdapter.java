package mediseen.pilltracker.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.realm.RealmResults;
import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Pill;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/21/2016.
 */
public class InventoryAdapter extends RecyclerView
        .Adapter<InventoryAdapter
        .ViewHolder> {
    private RealmResults<Pill> mDataset;
    private Context context;
    private Fragment frag;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextViewPlus pillName, dosage, amountInInventory;
        public ImageView circleLogo;
        public ButtonPlus editButton;
        public ViewHolder(View itemView) {
            super(itemView);
            pillName = (TextViewPlus) itemView.findViewById(R.id.pillName);
            dosage = (TextViewPlus) itemView.findViewById(R.id.dosage);
            circleLogo = (ImageView) itemView.findViewById(R.id.circleLogo);
            amountInInventory = (TextViewPlus) itemView.findViewById(R.id.amountInInventory);
            editButton = (ButtonPlus) itemView.findViewById(R.id.editButton);
        }
    }

    public InventoryAdapter(Context context, RealmResults<Pill> myDataset, Fragment frag) {
        this.context = context;
        mDataset = myDataset;
        this.frag = frag;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pill_inventory, parent, false);
        ViewHolder dataObjectHolder = new ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holderT, final int position) {
        final ViewHolder holder = holderT;

        Picasso.with(frag.getActivity()).load(R.drawable.tablet_icon).into(holder.circleLogo);
        Pill pill = mDataset.get(position);
        holder.pillName.setText(pill.getName());
        holder.dosage.setText(pill.getDosage());
//        if(pill.getAmountInInventory()>=100){
//            holder.amountInInventory.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
//        }
        holder.amountInInventory.setText(Integer.toString(pill.getAmountInInventory()));
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(frag, R.id.root_frame, EditPillsFragment.newInstance(position));
            }
        });
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}