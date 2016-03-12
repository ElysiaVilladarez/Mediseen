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

import io.realm.RealmResults;
import mediseen.CreateNoDisplay;
import mediseen.database.Pill;
import mediseen.database.ShoppingList;
import mediseen.pilltracker.adapters.ShoppingListAdapter;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/20/2016.
 */

public class ShoppingListFragment extends Fragment {
    private static FrameLayout tempLayout, wholeFrame;
    private static LinearLayout shoppingListLayout;
    private static View rootView;
    private static RecyclerView mRecyclerView;
    private static Fragment frag;
    private static RealmResults<ShoppingList> buy;
    public ShoppingListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        frag = this;
        shoppingListLayout = (LinearLayout) rootView.findViewById(R.id.shoppingListLayout);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.shoppingList);

        setShoppingListAdapter();

        return rootView;
    }

    public static void setShoppingListAdapter(){
//        buy = PillTracker.realm.allObjects(ShoppingList.class);
//        PillTracker.realm.beginTransaction();
//        buy.clear();
//        PillTracker.realm.commitTransaction();
        ArrayList<Pill> shop = new ArrayList<>();
        for(Pill p: PillTracker.give) {
            if (p.getAmountInInventory() <= p.getAmountTillShopping()) {
//                PillTracker.realm.beginTransaction();
//                ShoppingList buy = PillTracker.realm.createObject(ShoppingList.class);
//                buy.setPillToBuy(p);
//                PillTracker.realm.commitTransaction();
                shop.add(p);
            }
        }
//        buy = PillTracker.realm.allObjects(ShoppingList.class);
        if(shop.size()==0){
            shoppingListLayout.setVisibility(View.GONE);
            wholeFrame = (FrameLayout)rootView.findViewById(R.id.wholeFrame);

            tempLayout = (FrameLayout)CreateNoDisplay.noDisplay(frag.getResources().getString(R.string.noShoppingList), wholeFrame, frag);
            tempLayout.setVisibility(View.VISIBLE);
        }
        else{
            shoppingListLayout.setVisibility(View.VISIBLE);
            if(tempLayout!=null) {
                tempLayout.setVisibility(View.GONE);
            }
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(frag.getActivity()));
            ShoppingListAdapter adapter = new ShoppingListAdapter(frag.getActivity(), shop, frag);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
        System.out.println("CHECK: INVOKED");

    }
}