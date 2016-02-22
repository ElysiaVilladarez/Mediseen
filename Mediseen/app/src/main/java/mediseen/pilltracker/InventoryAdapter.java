package mediseen.pilltracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.RealmResults;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Pill;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/21/2016.
 */
public class InventoryAdapter extends RecyclerView
        .Adapter<InventoryAdapter
        .ViewHolder> {
    private ArrayList<String> mDataset;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextViewPlus pillName, dosage, amountInInventory;
        public ViewHolder(View itemView) {
            super(itemView);
//            pillName = (TextViewPlus) itemView.findViewById(R.id.pillName);
//            dosage = (TextViewPlus) itemView.findViewById(R.id.dosage);
//            amountInInventory = (TextViewPlus) itemView.findViewById(R.id.amountInInventory);

        }

        //Setting function for clicking an item
        @Override
        public void onClick(View v) {
        }
    }

    public InventoryAdapter(Context context, ArrayList<String> myDataset) {
        this.context = context;
        mDataset = myDataset;

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
    public void onBindViewHolder(ViewHolder holderT, int position) {
        final ViewHolder holder = holderT;
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}