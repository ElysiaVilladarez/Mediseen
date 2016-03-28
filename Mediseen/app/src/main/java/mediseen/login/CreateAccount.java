package mediseen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.MainActivity;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/28/2016.
 */
public class CreateAccount extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

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

        ((ButtonPlus) findViewById(R.id.createAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAccount.this, SetAccount.class);
                startActivity(i);
                finish();
            }
        });

    }
}
