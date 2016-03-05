package mediseen.healthhistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Notes;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class ViewNoteFragment extends Fragment {
    private final static String POSITION = "POSITION";

    public static ViewNoteFragment newInstance(int pos){
        ViewNoteFragment f = new ViewNoteFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, pos);
        f.setArguments(args);
        return f;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_view_notes, container, false);
        Notes note = HealthHistory.notes.get(getArguments().getInt(POSITION));
        ((TextViewPlus) rootView.findViewById(R.id.title)).setText(note.getTitle());
        ((TextViewPlus) rootView.findViewById(R.id.text)).setText(note.getText());
        SimpleDateFormat f = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        ((TextViewPlus) rootView.findViewById(R.id.date)).setText("Last updated " + f.format(note.getUpdatedDate()));

        ((ButtonPlus) rootView.findViewById(R.id.editButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(ViewNoteFragment.this, R.id.root_frame,
                        EditNotesFragment.newInstance(getArguments().getInt(POSITION)));
            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentReplace.replaceFragment(ViewNoteFragment.this, R.id.root_frame,
                        new NotesFragment());
            }
        });
        return rootView;
    }
}
