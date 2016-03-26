package mediseen.pilltracker.inventoryFragments;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import mediseen.customtextview.ButtonPlus;
import mediseen.helpers.DialogSize;
import mediseen.helpers.FragmentReplace;
import mediseen.database.Pill;
import mediseen.pilltracker.PillTracker;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/22/2016.
 */
public class AddPillsFragment extends Fragment {
    private Spinner spinner;
    final String prefName = "USER DATA";
    final String arrayKey = "DOSAGE OPTIONS";
    private ArrayList<String> dosageOptions;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_pill, container, false);
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

                    DialogSize.setSize(AddPillsFragment.this.getActivity(), dialog);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ((Button) rootView.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getActivity().getSharedPreferences(prefName,Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                Set<String> set = new HashSet<String>();
                set.addAll(dosageOptions);
                edit.putStringSet(arrayKey, set);
                edit.commit();
                FragmentReplace.replaceFragment(AddPillsFragment.this, R.id.root_frame, new InventoryFragment());
            }
        });

        ((Button) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pillName = ((EditText) rootView.findViewById(R.id.pillName)).getText().toString().trim().toUpperCase();
                String dosage = spinner.getSelectedItem().toString().trim();
                String amountInInventory = ((EditText) rootView.findViewById(R.id.amountInInventory)).getText().toString().trim();
                String amountInShopping = ((EditText) rootView.findViewById(R.id.amountInShopping)).getText().toString().trim();
                if (!pillName.isEmpty() && !dosage.isEmpty() && !amountInInventory.isEmpty() && !amountInShopping.isEmpty()) {
                    PillTracker.realm.beginTransaction();
                    Pill pill1 = PillTracker.realm.createObject(Pill.class);
                    pill1.setName(pillName);
                    pill1.setDosage(dosage);
                    pill1.setAmountTillShopping(Integer.parseInt(amountInShopping));
                    pill1.setAmountInInventory(Integer.parseInt(amountInInventory));
                    pill1.setUpdatedDate(Calendar.getInstance().getTime());
                    PillTracker.realm.commitTransaction();

                    SharedPreferences sp = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    Set<String> set = new HashSet<String>();
                    set.addAll(dosageOptions);
                    edit.putStringSet(arrayKey, set);
                    edit.commit();

                    FragmentReplace.replaceFragment(AddPillsFragment.this, R.id.root_frame, new InventoryFragment());
                } else {
                    Toast.makeText(getActivity(), R.string.fillDetails, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
