package mediseen.pilltracker.inventoryFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.FragmentReplace;
import mediseen.healthhistory.NotesFragment;
import mediseen.pilltracker.inventoryFragments.InventoryFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Example about replacing fragments inside a ViewPager. I'm using
 * android-support-v7 to maximize the compatibility.
 *
 * @author Dani Lao (@dani_lao)
 */
public class RootFragment extends Fragment {

//    private static final String ARG_SECTION_NUMBER = "POSITION";
//    private static final String TAG = "RootFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        FragmentReplace.replaceFragment(this, R.id.root_frame, new InventoryFragment());
        return view;
    }

}