package mediseen.pilltracker;

/**
 * Created by elysi on 2/20/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.R;

public class InventoryFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "POSITION";
    public static InventoryFragment newInstance(int position) {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }
    public InventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        TextViewPlus textView = (TextViewPlus) rootView.findViewById(R.id.text);
        textView.setText(getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;
    }
}