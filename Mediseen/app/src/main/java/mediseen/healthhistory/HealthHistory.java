package mediseen.healthhistory;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import mediseen.CreateNoDisplay;
import mediseen.customtextview.ButtonPlus;
import mediseen.pilltracker.RootFragment;
import mediseen.pilltracker.ShoppingListFragment;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class HealthHistory extends FragmentActivity {
    private ViewPager mViewPager;
    private HealthHistoryAdapter healthHistoryAdapter;
    public void onCreate(Bundle savedInstanceState){
        //Shares the same xml layout as InventoryFragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_tracker);

        //final View rootView = inflater.inflate(R.layout.fragment_health_history, container, false);
        healthHistoryAdapter = new HealthHistoryAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(healthHistoryAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setVisibility(View.GONE);

        //return rootView;
    }
    public class HealthHistoryAdapter extends FragmentPagerAdapter {

        public HealthHistoryAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return RootFragment.newInstance("Notes");
        }

        @Override
        public int getCount() {
            // Show 1 total pages.
            return 1;
        }
    }
}
