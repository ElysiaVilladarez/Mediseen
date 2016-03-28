package mediseen.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.MainActivity;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/28/2016.
 */
public class SetAccount extends AppCompatActivity {
    final static String USERNAME = "USERNAME";
    final static String PASSWORD = "PASSWORD";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_account);

        SpannableStringBuilder builder = new SpannableStringBuilder();

        String blue = "medi";
        SpannableString blueSpannable = new SpannableString(blue);
        blueSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.backgroundBlue)), 0, blue.length(), 0);
        builder.append(blueSpannable);

        String green = "seen";
        SpannableString greenSpannable = new SpannableString(green);
        greenSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.backgroundGreen)), 0, green.length(), 0);
        builder.append(greenSpannable);


        ((TextViewPlus) findViewById(R.id.mediseenLabel)).setText(builder, TextView.BufferType.SPANNABLE);
        Picasso.with(this).load(R.drawable.logomark).resizeDimen(R.dimen.logoMarkWidth,
                R.dimen.logoMarkHeight).into((ImageView) findViewById(R.id.logo));

        ((ButtonPlus) findViewById(R.id.createAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString().trim();
                String password = ((EditText) findViewById(R.id.password)).getText().toString().trim();
                String confirmPassword = ((EditText) findViewById(R.id.confirmPassword)).getText().toString().trim();
                if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(SetAccount.this, R.string.fillDetails, Toast.LENGTH_SHORT).show();
                } else {
                    if(password.equals(confirmPassword)) {
                        SharedPreferences prefs = SetAccount.this.getSharedPreferences(CheckStart.PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putString(USERNAME, username);
                        edit.putString(PASSWORD, password);
                        edit.commit();
                        Intent next = new Intent(SetAccount.this, MainActivity.class);
                        SetAccount.this.startActivity(next);
                        SetAccount.this.finish();
                    } else{
                        Toast.makeText(SetAccount.this, "Please confirm password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
