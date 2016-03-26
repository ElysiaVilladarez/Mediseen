package mediseen.pilltracker.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.realm.RealmResults;
import mediseen.helpers.DialogSize;
import mediseen.helpers.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Pill;
import mediseen.pilltracker.PillTracker;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.pilltracker.inventoryFragments.InventoryFragment;
import mediseen.pilltracker.shoppinglist.ShoppingListFragment;
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
        public ButtonPlus editButton, deleteButton;
        public ViewHolder(View itemView) {
            super(itemView);
            pillName = (TextViewPlus) itemView.findViewById(R.id.pillName);
            dosage = (TextViewPlus) itemView.findViewById(R.id.dosage);
            circleLogo = (ImageView) itemView.findViewById(R.id.circleLogo);
            amountInInventory = (TextViewPlus) itemView.findViewById(R.id.amountInInventory);
            editButton = (ButtonPlus) itemView.findViewById(R.id.editButton);
            deleteButton = (ButtonPlus) itemView.findViewById(R.id.deleteButton);
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
        final Pill pill = mDataset.get(position);
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
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(frag.getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete_are_you_sure);
                ((TextViewPlus)dialog.findViewById(R.id.deletingTitle)).setText("DELETING PILL");
                ((TextViewPlus)dialog.findViewById(R.id.message)).setText("Are you sure you want to delete this pill?");
                ((TextViewPlus)dialog.findViewById(R.id.title)).setText(pill.getName());
                ((TextViewPlus)dialog.findViewById(R.id.additionalInfo)).setText(pill.getDosage());
                ((ButtonPlus) dialog.findViewById(R.id.noButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ((ButtonPlus) dialog.findViewById(R.id.yesButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PillTracker.realm.beginTransaction();
                        pill.removeFromRealm();
                        PillTracker.realm.commitTransaction();
                        ShoppingListFragment.setShoppingListAdapter();
                        dialog.dismiss();
                        FragmentReplace.replaceFragment(frag, R.id.root_frame, new InventoryFragment());
                    }
                });

                dialog.show();


                DialogSize.setSize(frag.getActivity(), dialog);

            }
        });
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}