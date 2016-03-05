package mediseen.work.pearlsantos.mediseen;

/**
 * Created by Pearl Santos on 2/20/2016.
 */

    //import android.app.Activity;
    import android.app.SearchManager;
    import android.content.Context;
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
    import android.support.v7.app.*;
    import android.support.v7.widget.SearchView;
    import android.support.v7.widget.Toolbar;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.ArrayAdapter;
    import android.widget.ImageButton;
    import android.widget.ListView;
    // android.widget.Toolbar;

    import mediseen.viewgroup.FlyOutContainer;

    public class MainActivity extends AppCompatActivity {

        FlyOutContainer root;
        final String[] menu = {"Home", "Pill Tracker", "Health History", "General Information", "Account Settings", "Log Out"};


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_sample, null);
            Toolbar actionBarToolBar = (Toolbar) root.findViewById(R.id.toolbar);
            setSupportActionBar(actionBarToolBar);
//            Bitmap bm = BitmapFactory.decodeResource(getResources(),
//                    R.mipmap.ic_menu_black_24dp);
//            Bitmap bmInverted = createInvertedBitmap(bm);
//            Drawable d = new BitmapDrawable(getResources(), bmInverted);
            //actionBarToolBar.setNavigationIcon(d);
            ImageButton menuButton = (ImageButton) root.findViewById(R.id.menuButton);


            ListView listMenu = (ListView) root.findViewById(R.id.menu);
            ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, menu);
            listMenu.setAdapter(mAdapter);

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
            }
            if(searchView != null){
                searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            }

            return true;
        }

        public void toggleMenu(View v){
            this.root.toggleMenu();
        }

        private Bitmap createInvertedBitmap(Bitmap src) {
            ColorMatrix colorMatrix_Inverted =
                    new ColorMatrix(new float[] {
                            -1,  0,  0,  0, 255,
                            0, -1,  0,  0, 255,
                            0,  0, -1,  0, 255,
                            0,  0,  0,  1,   0});

            ColorFilter ColorFilter_Sepia = new ColorMatrixColorFilter(
                    colorMatrix_Inverted);

            Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);

            Paint paint = new Paint();

            paint.setColorFilter(ColorFilter_Sepia);
            canvas.drawBitmap(src, 0, 0, paint);

            return bitmap;
        }

    }


