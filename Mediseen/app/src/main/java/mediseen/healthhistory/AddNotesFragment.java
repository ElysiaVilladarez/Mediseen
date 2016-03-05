package mediseen.healthhistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.database.Notes;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class AddNotesFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_notes, container, false);
        ((ButtonPlus)rootView.findViewById(R.id.viewHistoryButton)).setVisibility(View.INVISIBLE);

        ((ButtonPlus) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HealthHistory.realm.beginTransaction();
                Notes n = HealthHistory.realm.createObject(Notes.class);
                n.setTitle(((EditText) rootView.findViewById(R.id.title)).getText().toString().trim());
                n.setText(((EditText) rootView.findViewById(R.id.text)).getText().toString().trim());
               // SimpleDateFormat f = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
                Calendar c = Calendar.getInstance();
                n.setUpdatedDate(c.getTime());
                HealthHistory.realm.commitTransaction();

                FragmentReplace.replaceFragment(AddNotesFragment.this, R.id.root_frame, ViewNoteFragment.newInstance(
                        HealthHistory.notes.size()-1));

            }
        });


        ((ButtonPlus) rootView.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(AddNotesFragment.this, R.id.root_frame, new NotesFragment());

            }
        });

        return rootView;
    }
}
