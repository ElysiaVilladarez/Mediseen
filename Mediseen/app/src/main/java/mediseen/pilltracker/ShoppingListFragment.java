package mediseen.pilltracker;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/20/2016.
 */

public class ShoppingListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "POSITION";
    private static boolean noShop = false;

    public ShoppingListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        ArrayList<String> lol = new ArrayList<>();
        final LinearLayout shoppingListLayout = (LinearLayout) rootView.findViewById(R.id.shoppingListLayout);
        if(noShop){
            shoppingListLayout.setVisibility(View.GONE);
            FrameLayout wholeFrame = (FrameLayout)rootView.findViewById(R.id.wholeFrame);

            final FrameLayout noPillsLayout = new FrameLayout(getActivity());
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

            ButtonPlus test = new ButtonPlus(getActivity());
            test.setText("TEST");

            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChangeVisibilityHelper.changeVisibility(shoppingListLayout, noPillsLayout);
                }
            });
            noPillsLayout.addView(logo);
            noPillsLayout.addView(noPillsText);
            noPillsLayout.addView(test);

            wholeFrame.addView(noPillsLayout);
        }
        else{
            shoppingListLayout.setVisibility(View.VISIBLE);

            lol.add("MEDICINE1");
            lol.add("MEDICINE2");
            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.shoppingList);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            ShoppingListAdapter adapter = new ShoppingListAdapter(getActivity(), lol);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
        return rootView;
    }
}