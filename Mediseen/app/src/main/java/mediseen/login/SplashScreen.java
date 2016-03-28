package mediseen.login;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/28/2016.
 */
public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash_screen);
        SpannableStringBuilder builder = new SpannableStringBuilder();

        String blue = "medi";
        SpannableString blueSpannable= new SpannableString(blue);
        blueSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.backgroundBlue)), 0, blue.length(), 0);
        builder.append(blueSpannable);

        String green = "seen";
        SpannableString greenSpannable= new SpannableString(green);
        greenSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.backgroundGreen)), 0, green.length(), 0);
        builder.append(greenSpannable);


        ((TextViewPlus) findViewById(R.id.mediseenLabel)).setText(builder, TextView.BufferType.SPANNABLE);
        Picasso.with(this).load(R.drawable.logomark).resizeDimen(R.dimen.logoMarkWidth,
                R.dimen.logoMarkHeight).into((ImageView) findViewById(R.id.logo));


        //Show Splash Screen for a few seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new CheckStart(getApplicationContext(), SplashScreen.this).execute();
            }
        }, SPLASH_DISPLAY_LENGTH);


    }

}
