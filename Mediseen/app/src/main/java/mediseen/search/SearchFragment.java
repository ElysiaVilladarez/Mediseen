package mediseen.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import mediseen.customtextview.ButtonPlus;
import mediseen.helpers.CreateNoDisplay;
import mediseen.helpers.FragmentReplace;
import mediseen.pilltracker.PillTracker;
import mediseen.pilltracker.inventoryFragments.AddPillsFragment;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/27/2016.
 */
public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.searchResults);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return rootView;
    }
}
