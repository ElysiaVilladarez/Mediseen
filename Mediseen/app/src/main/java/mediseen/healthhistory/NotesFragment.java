package mediseen.healthhistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import mediseen.CreateNoDisplay;
import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class NotesFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState){
        //Shares the same xml layout as InventoryFragment
        final View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        LinearLayout healthHisto = (LinearLayout) rootView.findViewById(R.id.pillInventoryLayout);
        ButtonPlus addNotesButton = (ButtonPlus) rootView.findViewById(R.id.addPillsButton);
        addNotesButton.setText(R.string.buttonTextAddNotes);


        addNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(NotesFragment.this, R.id.root_frame, new AddNotesFragment());
            }
        });

        if(HealthHistory.notes.size()==0){
            healthHisto.setVisibility(View.GONE);
            addNotesButton.setVisibility(View.VISIBLE);
            FrameLayout wholeFrame = (FrameLayout) rootView.findViewById(R.id.wholeFrame);

            FrameLayout tempLayout = (FrameLayout)CreateNoDisplay.noDisplay(getResources().getString(R.string.noNotes), wholeFrame, this);
            tempLayout.setVisibility(View.VISIBLE);

        } else {
            healthHisto.setVisibility(View.VISIBLE);
            addNotesButton.setVisibility(View.VISIBLE);

            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.pillInventory);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            NotesFragmentAdapter adapter = new NotesFragmentAdapter(getActivity(), HealthHistory.notes, this);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }

        return rootView;
    }
}
