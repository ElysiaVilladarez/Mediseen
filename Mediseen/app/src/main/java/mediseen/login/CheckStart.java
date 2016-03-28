package mediseen.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import mediseen.work.pearlsantos.mediseen.BuildConfig;


/**
 * Created by elysi on 3/28/2016.
 */
public class CheckStart extends AsyncTask<Void, Void, Integer> {
    private Context c;
    public static final String PREFS_NAME = "USER DATA";
    final String PREF_VERSION_CODE_KEY = "version_code";
    final int DOESNT_EXIST = -1;
    private Activity act;

    final static String WHAT_TO_DO = "NEXT_ACTION";


    public CheckStart(Context context, Activity a) {
        c = context;
        act = a;
    }

    public void onPreExecute() {


    }

    @Override
    protected Integer doInBackground(Void... params) {
        final int currentVersionCode = BuildConfig.VERSION_CODE;
        SharedPreferences prefs = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Get current version code
        if (prefs == null || prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST) == DOESNT_EXIST) {
            // TODO This is a new install (or the user cleared the shared preferences)
            // return new Intent(c, InstructionSlides.class);
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
            return 0;

        } else if (currentVersionCode == prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)) {
            //Normal run
            return 1;

        } else if (currentVersionCode > prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)) {
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
            return -1;

            // TODO This is an upgrade

        } else return -1;


    }

    @Override
    public void onPostExecute(Integer i) {
        Intent next = new Intent(act, CreateAccount.class);;
        if(i == 0) {
            next = new Intent(act, CreateAccount.class);
        } else if(i == 1){
            next = new Intent(act, LoginPage.class);
        }
        act.startActivity(next);
//            act.overridePendingTransition(R.anim.pull_in_right,
//                    R.anim.push_out_left);
        act.finish();

    }

}

