package mediseen.pilltracker.shoppinglist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.helpers.FragmentReplace;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/10/2016.
 */
public class RootFragmentShopping extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root_shop, container, false);
        FragmentReplace.replaceFragment(this, R.id.root_frame_shop, new ShoppingListFragment());
        return view;
    }

}

