package mediseen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/3/2016.
 */
public class FragmentReplace {
    public static void replaceFragment(Fragment currentFragment, int root_frame, Fragment newFragment){
        FragmentTransaction trans = currentFragment.getFragmentManager()
                .beginTransaction();

        trans.replace(root_frame, newFragment);
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.commit();
    }
}
