package mediseen.pilltracker.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import mediseen.database.PillHistory;
import mediseen.helpers.DialogSize;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Pill;
import mediseen.helpers.FragmentReplace;
import mediseen.pilltracker.PillTracker;
import mediseen.pilltracker.shoppinglist.ShoppingListFragment;
import mediseen.pilltracker.inventoryFragments.InventoryFragment;
import mediseen.work.pearlsantos.mediseen.R;

public class ShoppingListAdapter extends RecyclerView
        .Adapter<ShoppingListAdapter
        .ViewHolder> {
    private ArrayList<Pill> mDataset;
    private Context context;
    private Fragment frag;


    public class ViewHolder extends RecyclerView.ViewHolder {
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

    public ShoppingListAdapter(Context context, ArrayList<Pill> myDataset, Fragment frag) {
        this.context = context;
        mDataset = myDataset;
        this.frag = frag;

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

        Picasso.with(context).load(R.drawable.tablet_icon).into(holder.circleLogo);
        final Pill pill = mDataset.get(position);
        holder.pillName.setText(pill.getName());
        holder.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_update_inventory);
                //dialog.setTitle("T");

                ((ButtonPlus) dialog.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ((ButtonPlus) dialog.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText pillsBought = (EditText) dialog.findViewById(R.id.pillsBought);

                        PillHistory histo = new PillHistory();
                        histo.setCreatedDate(pill.getUpdatedDate());
                        histo.setAmountInInventory(pill.getAmountInInventory());

                        PillTracker.realm.beginTransaction();
                        Pill p = PillTracker.realm.createObject(Pill.class);
                        p.setName(pill.getName());
                        p.setDosage(pill.getDosage());
                        p.setAmountInInventory(pill.getAmountInInventory() +
                                Integer.parseInt(pillsBought.getText().toString().trim()));
                        p.setAmountTillShopping(pill.getAmountTillShopping());
                        p.setUpdatedDate(Calendar.getInstance().getTime());
                        p.setEditHistories(pill.getEditHistories());
                        p.getEditHistories().add(histo);
                        pill.removeFromRealm();
                        PillTracker.realm.commitTransaction();

                        mDataset.remove(position);
                        notifyDataSetChanged();

                        InventoryFragment.setInventoryAdapter();

                        FragmentTransaction transaction = frag.getFragmentManager().beginTransaction();
                        transaction.replace(R.id.root_frame_shop, new ShoppingListFragment());
                        transaction.commit();

                        dialog.dismiss();
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