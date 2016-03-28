package mediseen.work.pearlsantos.mediseen;

/**
 * Created by Pearl Santos on 2/20/2016.
 */

    //import android.app.Activity;
    import android.app.SearchManager;
    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.graphics.ColorFilter;
    import android.graphics.ColorMatrix;
    import android.graphics.ColorMatrixColorFilter;
    import android.graphics.Paint;
    import android.graphics.drawable.BitmapDrawable;
    import android.graphics.drawable.Drawable;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v7.app.*;
    import android.view.ViewGroup;
    import android.widget.SearchView;
    import android.support.v7.widget.Toolbar;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ImageButton;
    import android.widget.ImageView;
    import android.widget.ListView;
    import android.widget.TextView;
    // android.widget.Toolbar;

    import mediseen.customtextview.TextViewPlus;
    import mediseen.generalinfo.GeneralInfo;
    import mediseen.healthhistory.HealthHistory;
    import mediseen.accountsettings.AccountSettings;
    import mediseen.helpers.FragmentReplace;
    import mediseen.home.Greeting;
    import mediseen.login.LoginPage;
    import mediseen.pilltracker.PillTracker;
    import mediseen.search.SearchFragment;
    import mediseen.viewgroup.FlyOutContainer;

    public class MainActivity extends AppCompatActivity {

        FlyOutContainer root;
        final String[] menu = {"Home", "Pill Tracker", "Health History", "General Information", "Account Settings", "Log Out"};
        ListView listMenu;
        TextView title;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_sample, null);
            Toolbar actionBarToolBar = (Toolbar) root.findViewById(R.id.toolbar);
            setSupportActionBar(actionBarToolBar);
            title = (TextView) root.findViewById(R.id.appTitle);


            listMenu = (ListView) root.findViewById(R.id.menu);
            ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, menu);
            listMenu.setAdapter(mAdapter);
            listMenu.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listMenu.setOnItemClickListener(new DrawerItemClickListener());
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Greeting()).commit();
            listMenu.setItemChecked(0, true);
            title.setText(menu[0]); //setting the title


            this.setContentView(root);


        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.sample, menu);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

            SearchView searchView = (SearchView) searchItem.getActionView();
            int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
            ImageView v = (ImageView) searchView.findViewById(searchImgId);
            v.setImageResource(R.mipmap.ic_search_white_24dp);
            int searchHintId = searchView.getResources().getIdentifier("android:id/search_mag_icon", null, null);
            ImageView w = (ImageView) searchView.findViewById(searchHintId);
            w.setImageResource(R.mipmap.ic_search_white_24dp);
            changeSearchViewTextColor(searchView);
//            .search_mag_icon)



            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, SearchFragment.newInstance(query)).commit();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });


            return true;
        }
        @Override
        public boolean onPrepareOptionsMenu(Menu menu){
            return super.onPrepareOptionsMenu(menu);

        }

        public void toggleMenu(View v){
            this.root.toggleMenu();
        }

        private class DrawerItemClickListener implements ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextViewPlus text = (TextViewPlus) view.findViewById(R.id.menu_item);
//                text.setBackgroundColor(Color.parseColor(getResources().getString(R.string.backgroundGray)));
                selectItem(position);
            }
        }

        private void selectItem(int position) {
            // update the main content by replacing fragments
            Fragment fragment = null;
            //Bundle args = new Bundle();

            switch(position){
                case 0:
                    fragment = new Greeting();
                    root.toggleMenu();
                    break;
                case 1:
                    fragment = new PillTracker();
                    root.toggleMenu();
                    break;
                case 2:
                    fragment = new HealthHistory();
                    root.toggleMenu();
                    break;
                case 3:
                    fragment = new GeneralInfo(); //should be general information
                    root.toggleMenu();
                    break;
                case 4:
                    fragment = new AccountSettings();
                    root.toggleMenu();
                    break;
                case 5:
                    logout(); //logout
                    break;
                default:
                    break;
            }
            if(fragment != null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }


            // update selected item and title, then close the drawer
            listMenu.setItemChecked(position, true);
            title.setText(menu[position]); //setting the title

        }

        public void logout(){
            Intent i = new Intent(this, LoginPage.class);
            startActivity(i);
            finish();
        }

        private void changeSearchViewTextColor(View view) {
            if (view != null) {
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(Color.WHITE);
                    return;
                } else if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        changeSearchViewTextColor(viewGroup.getChildAt(i));
                    }
                }
            }
        }
    }





