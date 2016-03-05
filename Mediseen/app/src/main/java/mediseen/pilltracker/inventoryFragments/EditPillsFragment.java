package mediseen.pilltracker.inventoryFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import java.util.Calendar;

import mediseen.DialogSize;
import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Pill;
import mediseen.database.PillHistory;
import mediseen.pilltracker.PillTracker;
import mediseen.pilltracker.adapters.PillEditHistoryAdapter;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class EditPillsFragment extends Fragment {
    private final static String POSITION = "POSITION";
    private EditText pillName, dosage, amountInInventory;

    public static EditPillsFragment newInstance(int pos) {
        EditPillsFragment f = new EditPillsFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, pos);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_pills, container, false);

        final Pill pill = PillTracker.give.get(getArguments().getInt(POSITION));

        pillName = (EditText) rootView.findViewById(R.id.pillName);
        dosage = (EditText) rootView.findViewById(R.id.dosage);
        amountInInventory = (EditText) rootView.findViewById(R.id.amountInInventory);
        pillName.setText(pill.getName());
        dosage.setText(pill.getDosage());
        amountInInventory.setText(Integer.toString(pill.getAmountInInventory()));

        ((ButtonPlus) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pill p = new Pill();
                p.setName(pillName.getText().toString().trim());
                p.setDosage(dosage.getText().toString().trim());
                p.setAmountInInventory(Integer.parseInt(amountInInventory.getText().toString().trim()));
                p.setUpdatedDate(Calendar.getInstance().getTime());

                PillHistory histo = new PillHistory();
                histo.setCreatedDate(pill.getUpdatedDate());
                histo.setAmountInInventory(pill.getAmountInInventory());

                PillTracker.realm.beginTransaction();
                p.setEditHistories(pill.getEditHistories());
                p.getEditHistories().add(histo);
                PillTracker.realm.copyToRealm(p);
                pill.removeFromRealm();
                PillTracker.realm.commitTransaction();

                FragmentReplace.replaceFragment(EditPillsFragment.this, R.id.root_frame, new InventoryFragment());
            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(EditPillsFragment.this, R.id.root_frame, new InventoryFragment());

            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.viewHistoryButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_pill_edit_history);

                ((TextViewPlus)dialog.findViewById(R.id.pillName)).setText(pill.getName());
                ((TextViewPlus)dialog.findViewById(R.id.dosage)).setText(pill.getDosage());

                RecyclerView mRecyclerView = (RecyclerView) dialog.findViewById(R.id.pillHistory);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                PillEditHistoryAdapter adapter = new PillEditHistoryAdapter(getActivity(), pill.getEditHistories(), EditPillsFragment.this);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.getAdapter().notifyDataSetChanged();



                ((ButtonPlus) dialog.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ((ButtonPlus) dialog.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


                DialogSize.setSize(EditPillsFragment.this.getActivity(), dialog);
            }
        });
        return rootView;
    }
}


