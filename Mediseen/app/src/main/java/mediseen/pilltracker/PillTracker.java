package mediseen.pilltracker;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import mediseen.customtextview.CustomFontHelper;
import mediseen.database.Pill;
import mediseen.pilltracker.inventoryFragments.RootFragment;
import mediseen.pilltracker.shoppinglist.RootFragmentShopping;
import mediseen.work.pearlsantos.mediseen.R;

public class PillTracker extends Fragment {

    private PillTrackerAdapter mPillTrackerAdapter;
    private String HEADER_FONT = "fonts/SourceSansPro-Black.otf";

    public static RealmResults<Pill> give;
    public static Realm realm;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_pill_tracker, container, false);
        super.onCreate(savedInstanceState);

        mPillTrackerAdapter = new PillTrackerAdapter(getChildFragmentManager());

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPillTrackerAdapter);
        mViewPager.getAdapter().notifyDataSetChanged();

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if(tab.getPosition()==0) {
//                    mViewPager.setAdapter(mPillTrackerAdapter);
//                    tabLayout.getTabAt(0).select();
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    TextView header = (TextView) tabViewChild;
                    CustomFontHelper.setCustomFont(header, HEADER_FONT, getActivity().getApplicationContext());
                    header.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),
                            R.color.headerColor));
                }
            }
        }


        realm = Realm.getInstance(getActivity().getApplicationContext());

//        realm.beginTransaction();
//        Pill pill1 = realm.createObject(Pill.class);
//        pill1.setName("MEDICINE 2");
//        pill1.setDosage("2 pills per day");
//        pill1.setAmountTillShopping(3);
//        pill1.setAmountInInventory(20);
//        realm.commitTransaction();

        give = realm.allObjects(Pill.class);


        return rootView;

    }



    public class PillTrackerAdapter extends FragmentStatePagerAdapter {

        public PillTrackerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    System.out.println("lol");
                    return new RootFragment();
                case 1:
                    System.out.println("lol2");
                    return new RootFragmentShopping();
            }
            return null;

        }

        @Override
        public int getCount() {
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
