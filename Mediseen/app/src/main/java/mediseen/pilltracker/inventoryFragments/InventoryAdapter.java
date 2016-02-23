package mediseen.pilltracker.inventoryFragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/21/2016.
 */
public class InventoryAdapter extends RecyclerView
        .Adapter<InventoryAdapter
        .ViewHolder> {
    private ArrayList<String> mDataset;
    private Context context;
    private Fragment frag;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextViewPlus pillName, dosage, amountInInventory;
        public ButtonPlus editButton;
        public ViewHolder(View itemView) {
            super(itemView);
            pillName = (TextViewPlus) itemView.findViewById(R.id.pillName);
            dosage = (TextViewPlus) itemView.findViewById(R.id.dosage);
            amountInInventory = (TextViewPlus) itemView.findViewById(R.id.amountInInventory);
            editButton = (ButtonPlus) itemView.findViewById(R.id.editButton);
        }
    }

    public InventoryAdapter(Context context, ArrayList<String> myDataset, Fragment frag) {
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
    public void onBindViewHolder(ViewHolder holderT, int position) {
        final ViewHolder holder = holderT;

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = frag.getFragmentManager()
                        .beginTransaction();
				/*
				 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
                trans.replace(R.id.root_frame, new EditPillsFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}