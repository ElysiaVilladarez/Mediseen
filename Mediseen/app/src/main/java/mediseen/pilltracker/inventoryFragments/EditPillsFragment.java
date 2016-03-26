package mediseen.pilltracker.inventoryFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import mediseen.helpers.DialogSize;
import mediseen.helpers.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Pill;
import mediseen.database.PillHistory;
import mediseen.pilltracker.PillTracker;
import mediseen.pilltracker.shoppinglist.ShoppingListFragment;
import mediseen.pilltracker.adapters.PillEditHistoryAdapter;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class EditPillsFragment extends Fragment {
    private final static String POSITION = "POSITION";
    private EditText pillName, amountInInventory;

    Spinner spinner;
    final String prefName = "USER DATA";
    final String arrayKey = "DOSAGE OPTIONS";
    private ArrayList<String> dosageOptions;

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
        //dosage = (Spinner) rootView.findViewById(R.id.dosage);
        amountInInventory = (EditText) rootView.findViewById(R.id.amountInInventory);
        pillName.setText(pill.getName());
        amountInInventory.setText(Integer.toString(pill.getAmountInInventory()));
        SharedPreferences sp = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        Set<String> set = sp.getStringSet(arrayKey, null);

        if(set==null){
            System.out.println("CHECK: NULL");
            dosageOptions = new ArrayList<String>();
            dosageOptions.add("1 per hour");
            dosageOptions.add("1 every 2 hours");
            dosageOptions.add("1 every 6 hours");
            dosageOptions.add("1 every 8 hours");
            dosageOptions.add("1 every day");
            dosageOptions.add("Custom");
        } else{
            dosageOptions = new ArrayList<String>(set);
        }

        spinner = (Spinner) rootView.findViewById(R.id.dosage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dosageOptions);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Custom")){
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_custom_dosage);

                    final NumberPicker np = (NumberPicker) dialog.findViewById(R.id.pillNumber);
                    np.setMaxValue(100);
                    np.setMinValue(1);
                    np.setWrapSelectorWheel(false);

                    ((ButtonPlus) dialog.findViewById(R.id.createButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String frequency = ((EditText) dialog.findViewById(R.id.frequency)).getText().toString().trim();
                            if(frequency.isEmpty()){
                                Toast.makeText(getActivity(), R.string.fillDetails, Toast.LENGTH_SHORT).show();
                            } else {
                                String newDosage = String.valueOf(np.getValue()) + " " + frequency;
                                dosageOptions.remove("Custom");
                                dosageOptions.add(newDosage);
                                dosageOptions.add("Custom");
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dosageOptions);
                                spinner.setAdapter(adapter);
                                spinner.setSelection(adapter.getPosition(newDosage));
                                dialog.dismiss();
                            }
                        }
                    });

                    ((ButtonPlus) dialog.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                    DialogSize.setSize(EditPillsFragment.this.getActivity(), dialog);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PillHistory histo = new PillHistory();
                histo.setCreatedDate(pill.getUpdatedDate());
                histo.setAmountInInventory(pill.getAmountInInventory());
                PillTracker.realm.beginTransaction();
                Pill p = PillTracker.realm.createObject(Pill.class);
                p.setName(pillName.getText().toString().trim().toUpperCase());
                p.setDosage(spinner.getSelectedItem().toString().trim());
                p.setAmountInInventory(Integer.parseInt(amountInInventory.getText().toString().trim()));
                p.setUpdatedDate(Calendar.getInstance().getTime());
                p.setEditHistories(pill.getEditHistories());
                p.setAmountTillShopping(pill.getAmountTillShopping());
                p.getEditHistories().add(histo);
                pill.removeFromRealm();
                PillTracker.realm.commitTransaction();

                SharedPreferences sp = getActivity().getSharedPreferences(prefName,Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                Set<String> set = new HashSet<String>();
                set.addAll(dosageOptions);
                edit.putStringSet(arrayKey, set);
                edit.commit();

                FragmentReplace.replaceFragment(EditPillsFragment.this, R.id.root_frame, new InventoryFragment());
                ShoppingListFragment.setShoppingListAdapter();
            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getActivity().getSharedPreferences(prefName,Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                Set<String> set = new HashSet<String>();
                set.addAll(dosageOptions);
                edit.putStringSet(arrayKey, set);
                edit.commit();

                FragmentReplace.replaceFragment(EditPillsFragment.this, R.id.root_frame, new InventoryFragment());

            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.viewHistoryButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_pill_edit_history);

                ((TextViewPlus) dialog.findViewById(R.id.pillName)).setText(pill.getName());
                ((TextViewPlus) dialog.findViewById(R.id.dosage)).setText(pill.getDosage());

                RecyclerView mRecyclerView = (RecyclerView) dialog.findViewById(R.id.pillHistory);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                PillEditHistoryAdapter adapter = new PillEditHistoryAdapter(getActivity(), pill.getEditHistories(), EditPillsFragment.this);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.getAdapter().notifyDataSetChanged();


                ((ButtonPlus) dialog.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
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