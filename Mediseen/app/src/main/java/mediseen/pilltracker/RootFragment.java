package mediseen.pilltracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.healthhistory.NotesFragment;
import mediseen.pilltracker.inventoryFragments.InventoryFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Example about replacing fragments inside a ViewPager. I'm using
 * android-support-v7 to maximize the compatibility.
 *
 * @author Dani Lao (@dani_lao)
 *
 */
public class RootFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "POSITION";
    private static final String TAG = "RootFragment";

    public static RootFragment newInstance(String fragmentDisplay){
        RootFragment rf = new RootFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, fragmentDisplay);
        rf.setArguments(args);
        return rf;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_root, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        String fragment = getArguments().getString(ARG_SECTION_NUMBER);
        if(fragment.equals("Inventory")) {
            transaction.replace(R.id.root_frame, new InventoryFragment());
        } else if (fragment.equals("Notes")){
            transaction.replace(R.id.root_frame, new NotesFragment());
        }


        transaction.commit();

        return view;
    }

}