package mediseen.healthhistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Notes;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/3/2016.
 */
public class EditNotesFragment extends Fragment {
    private final static String POSITION = "POSITION";
    private EditText title, text;

    public static EditNotesFragment newInstance(int pos){
        EditNotesFragment f = new EditNotesFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, pos);
        f.setArguments(args);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_notes, container, false);
        final Notes note = HealthHistory.notes.get(getArguments().getInt(POSITION));

        title = (EditText)rootView.findViewById(R.id.title);
        text = (EditText)rootView.findViewById(R.id.text);
        title.setText(note.getTitle());
        text.setText(note.getText());

                ((ButtonPlus) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HealthHistory.realm.beginTransaction();
                        Notes n = HealthHistory.realm.createObject(Notes.class);
                        n.getEditHistories().add(note);
                        note.removeFromRealm();
                        n.setTitle(title.getText().toString().trim());
                        n.setText(text.getText().toString().trim());
//                        SimpleDateFormat f = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
                        Calendar c = Calendar.getInstance();
                        n.setUpdatedDate(c.getTime());
                        HealthHistory.realm.commitTransaction();

                        FragmentReplace.replaceFragment(EditNotesFragment.this, R.id.root_frame, ViewNoteFragment.newInstance(
                                getArguments().getInt(POSITION)));

                    }
                });


        ((ButtonPlus) rootView.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(EditNotesFragment.this, R.id.root_frame, new NotesFragment());

            }
        });

        return rootView;
    }
}
