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
    import android.support.v7.widget.SearchView;
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

    import mediseen.healthhistory.HealthHistory;
    import mediseen.home.Greeting;
    import mediseen.pilltracker.PillTracker;
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
            listMenu.setOnItemClickListener(new DrawerItemClickListener());
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Greeting()).commit();



            this.setContentView(root);


        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.sample, menu);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

            SearchView searchView = null;
            if(searchItem != null){
                searchView = (SearchView) searchItem.getActionView();
//                int searchImgId = this.getResources().getIdentifier("android:id/search_button", null, null);
//                ImageView v = (ImageView) searchView.findViewById(searchImgId);
//                v.setImageResource(R.mipmap.ic_search_white_24dp);
            }
            if(searchView != null){
                searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            }


            //searchView.setOnQueryTextListener(this);


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
                selectItem(position);
            }
        }

        private void selectItem(int position) {
            // update the main content by replacing fragments
            Fragment fragment = null;
            Bundle args = new Bundle();

            switch(position){
                case 0:
                    fragment = new Greeting();
                    break;
                case 1:
                    fragment = new PillTracker();
                    break;
                case 2:
                    fragment = new HealthHistory();
                    break;
                case 3:
                    fragment = new Greeting(); //should be general information
                    break;
                case 4:
                    fragment = new Greeting(); //account settings
                    break;
                case 5:
                    fragment = new Greeting(); //logout
                    break;
                default:
                    break;
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            // update selected item and title, then close the drawer
            listMenu.setItemChecked(position, true);
            title.setText(menu[position]); //setting the title
            root.toggleMenu();
        }




    }





