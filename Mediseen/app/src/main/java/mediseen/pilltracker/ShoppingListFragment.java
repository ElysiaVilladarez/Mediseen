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

import mediseen.CreateNoDisplay;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/20/2016.
 */

public class ShoppingListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "POSITION";
    private static boolean noShop = true;

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

            CreateNoDisplay.noDisplay(getResources().getString(R.string.noShoppingList), wholeFrame, this);

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