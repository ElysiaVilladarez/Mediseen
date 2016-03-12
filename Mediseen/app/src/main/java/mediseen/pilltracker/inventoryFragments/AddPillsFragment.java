package mediseen.pilltracker.inventoryFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;

import io.realm.Realm;
import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.database.Pill;
import mediseen.pilltracker.PillTracker;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/22/2016.
 */
public class AddPillsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_pill, container, false);
        ((Button) rootView.findViewById(R.id.test)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getInstance(getActivity().getApplicationContext());
                realm.beginTransaction();
                Pill pill1 = realm.createObject(Pill.class);
                pill1.setName("MEDICINE 2");
                pill1.setDosage("2 pills per day");
                pill1.setAmountTillShopping(3);
                pill1.setAmountInInventory(20);
                pill1.setUpdatedDate(Calendar.getInstance().getTime());
                realm.commitTransaction();
                FragmentReplace.replaceFragment(AddPillsFragment.this, R.id.root_frame, new InventoryFragment());
            }
        });
        return rootView;
    }
}
