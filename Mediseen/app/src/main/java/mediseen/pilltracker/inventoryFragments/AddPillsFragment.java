package mediseen.pilltracker.inventoryFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;

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
                Pill pill = new Pill();
                pill.setName("MEDICINE 2");
                pill.setDosage("2 pills per day");
                pill.setAmountInInventory(15);
                pill.setUpdatedDate(Calendar.getInstance().getTime());
                pill.setAmountTillShopping(30);

                PillTracker.realm.beginTransaction();
                PillTracker.realm.copyToRealm(pill);
                PillTracker.realm.commitTransaction();


                FragmentReplace.replaceFragment(AddPillsFragment.this, R.id.root_frame, new InventoryFragment());
            }
        });
        return rootView;
    }
}
