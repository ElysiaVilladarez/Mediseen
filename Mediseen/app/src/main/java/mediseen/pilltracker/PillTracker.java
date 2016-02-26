package mediseen.pilltracker;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mediseen.customtextview.CustomFontHelper;
import mediseen.work.pearlsantos.mediseen.R;

public class PillTracker extends FragmentActivity {

    private PillTrackerAdapter mPillTrackerAdapter;
    private String HEADER_FONT = "fonts/SourceSansPro-Black.otf";
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_tracker);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPillTrackerAdapter = new PillTrackerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPillTrackerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    TextView header = (TextView) tabViewChild;
                    CustomFontHelper.setCustomFont(header, HEADER_FONT, getApplicationContext());
                    header.setTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.headerColor));
                }
            }
        }


    }



    public class PillTrackerAdapter extends FragmentPagerAdapter {

        public PillTrackerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RootFragment.newInstance("Inventory");
                case 1:
                    return new ShoppingListFragment();
            }
            return null;

        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "INVENTORY";
                case 1:
                    return "SHOPPING LIST";
            }
            return null;
        }
    }


}
