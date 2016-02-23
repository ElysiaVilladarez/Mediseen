package mediseen.pilltracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.pilltracker.inventoryFragments.InventoryFragment;
import mediseen.work.pearlsantos.mediseen.R;

public class RootShoppingFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "POSITION";
    private static final String TAG = "RootFragment";


    public RootShoppingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_root_shopping, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        transaction.replace(R.id.root_frame, new ShoppingListFragment());


        transaction.commit();

        return view;
    }
}
