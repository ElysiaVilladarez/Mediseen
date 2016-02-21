package mediseen.pilltracker;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
        if(true){
            FrameLayout wholeFrame = (FrameLayout)rootView.findViewById(R.id.wholeFrame);

            FrameLayout noPillsLayout = new FrameLayout(getActivity());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            noPillsLayout.setLayoutParams(layoutParams);

            ImageView logo = new ImageView(getActivity());
            Picasso.with(getActivity()).load(R.drawable.logomark).resizeDimen(R.dimen.logoMarkWidth,
                    R.dimen.logoMarkHeight).into(logo);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams2.gravity= Gravity.TOP|Gravity.CENTER_HORIZONTAL;
            logo.setLayoutParams(layoutParams2);


            TextViewPlus noPillsText = new TextViewPlus(getActivity());
            noPillsText.setText(R.string.noShoppingList);
            noPillsText.setGravity(Gravity.START);
            if(Build.VERSION.SDK_INT < 23){
                noPillsText.setTextAppearance(getActivity(), R.style.bigTextStyle);
            }
            else{
                noPillsText.setTextAppearance(R.style.bigTextStyle);
            }
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams3.gravity=Gravity.CENTER;
            noPillsText.setLayoutParams(layoutParams3);

            noPillsLayout.addView(logo);
            noPillsLayout.addView(noPillsText);

            wholeFrame.addView(noPillsLayout);
        }        return rootView;
    }
}