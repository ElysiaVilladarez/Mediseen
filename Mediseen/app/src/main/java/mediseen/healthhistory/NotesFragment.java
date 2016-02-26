package mediseen.healthhistory;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import mediseen.CreateNoDisplay;
import mediseen.customtextview.ButtonPlus;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.pilltracker.inventoryFragments.InventoryAdapter;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class NotesFragment extends Fragment {
    public static boolean noNotes = false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState){
        //Shares the same xml layout as InventoryFragment
        final View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        LinearLayout healthHisto = (LinearLayout) rootView.findViewById(R.id.pillInventoryLayout);
        ButtonPlus addNotesButton = (ButtonPlus) rootView.findViewById(R.id.addPillsButton);
        addNotesButton.setText(R.string.buttonTextAddNotes);
        ArrayList<String> lol = new ArrayList<>();


        addNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                /*
				 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
                trans.replace(R.id.root_frame, new AddNotesFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.commit();
            }
        });
        if(noNotes){

            healthHisto.setVisibility(View.GONE);
            addNotesButton.setVisibility(View.VISIBLE);
            FrameLayout wholeFrame = (FrameLayout) rootView.findViewById(R.id.wholeFrame);

            CreateNoDisplay.noDisplay(getResources().getString(R.string.noNotes), wholeFrame, this);



        } else {
            healthHisto.setVisibility(View.VISIBLE);
            addNotesButton.setVisibility(View.VISIBLE);

            lol.add("MEDICINE1");
            lol.add("MEDICINE2");
            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.pillInventory);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            NotesFragmentAdapter adapter = new NotesFragmentAdapter(getActivity(), lol, this);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);


        }

        return rootView;
    }
}
