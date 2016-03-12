package mediseen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.MainActivity;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/10/2016.
 */
public class LoginPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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


    }

    public void login(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
