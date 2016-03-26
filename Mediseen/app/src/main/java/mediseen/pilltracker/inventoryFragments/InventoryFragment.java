package mediseen.pilltracker.inventoryFragments;

/**
 * Created by elysi on 2/20/2016.
 */

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

import mediseen.helpers.CreateNoDisplay;
import mediseen.helpers.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.pilltracker.PillTracker;
import mediseen.pilltracker.adapters.InventoryAdapter;
import mediseen.work.pearlsantos.mediseen.R;

public class InventoryFragment extends Fragment {
    private FrameLayout tempLayout;
    private static RecyclerView mRecyclerView;
    private static Fragment frag;
  //  private final String TYPEFACE = "fonts/SignikaNegative-Regular_0.ttf";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        LinearLayout pillInventoryLayout = (LinearLayout) rootView.findViewById(R.id.pillInventoryLayout);
        ButtonPlus addPillsButton = (ButtonPlus) rootView.findViewById(R.id.addPillsButton);


        frag = this;
        addPillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(InventoryFragment.this, R.id.root_frame, new AddPillsFragment());
            }
        });

        if (PillTracker.give.size()==0) {
            pillInventoryLayout.setVisibility(View.GONE);
            addPillsButton.setVisibility(View.VISIBLE);
            FrameLayout wholeFrame = (FrameLayout) rootView.findViewById(R.id.wholeFrame);

            tempLayout = (FrameLayout) CreateNoDisplay.noDisplay(getResources().getString(R.string.noPills), wholeFrame, this);
            tempLayout.setVisibility(View.VISIBLE);

        } else {
            pillInventoryLayout.setVisibility(View.VISIBLE);
            addPillsButton.setVisibility(View.VISIBLE);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.pillInventory);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            setInventoryAdapter();

        }


        return rootView;
    }

    public static void setInventoryAdapter(){
        InventoryAdapter adapter = new InventoryAdapter(frag.getActivity(), PillTracker.give, frag);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();

    }
}