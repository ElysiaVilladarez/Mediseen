package mediseen.pilltracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/20/2016.
 */

public class ShoppingListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "POSITION";

    public static ShoppingListFragment newInstance(int position) {
        ShoppingListFragment fragment = new ShoppingListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public ShoppingListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        TextViewPlus textView = (TextViewPlus) rootView.findViewById(R.id.text);
        textView.setText(getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;
    }
}