package mediseen.pilltracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import mediseen.CreateNoDisplay;
import mediseen.database.Pill;
import mediseen.pilltracker.adapters.ShoppingListAdapter;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/20/2016.
 */

public class ShoppingListFragment extends Fragment {
    private FrameLayout tempLayout, wholeFrame;
    private LinearLayout shoppingListLayout;
    private ArrayList<Pill> shop;
    public ShoppingListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        shoppingListLayout = (LinearLayout) rootView.findViewById(R.id.shoppingListLayout);
        shop = new ArrayList<>();
        for(int i=0; i<PillTracker.give.size();i++){
            System.out.println("CHECK: INSERT IN FOR LOOP SHOPPING LIST FRAG");
            Pill toShop = PillTracker.give.get(i);
            System.out.println("CHECK:" + toShop.getAmountInInventory());
            System.out.println("CHECK:" +toShop.getAmountTillShopping());
            if(toShop.getAmountInInventory()<=toShop.getAmountTillShopping()){
                System.out.println("CHECK: INSERT IN FOR LOOP SHOPPING LIST FRAG IF");
                shop.add(toShop);
            }
        }
        if(shop.size()==0){
            shoppingListLayout.setVisibility(View.GONE);
            wholeFrame = (FrameLayout)rootView.findViewById(R.id.wholeFrame);

            tempLayout = (FrameLayout)CreateNoDisplay.noDisplay(getResources().getString(R.string.noShoppingList), wholeFrame, this);
            tempLayout.setVisibility(View.VISIBLE);
        }
        else{
            shoppingListLayout.setVisibility(View.VISIBLE);

            RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.shoppingList);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            ShoppingListAdapter adapter = new ShoppingListAdapter(getActivity(), shop, this);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
        return rootView;
    }
}