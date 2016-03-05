package mediseen.healthhistory;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import mediseen.DialogSize;
import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Notes;
import mediseen.database.NotesHistory;
import mediseen.pilltracker.adapters.PillEditHistoryAdapter;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/3/2016.
 */
public class EditNotesFragment extends Fragment {
    private final static String POSITION = "POSITION";
    private EditText title, text;

    public static EditNotesFragment newInstance(int pos) {
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

        title = (EditText) rootView.findViewById(R.id.title);
        text = (EditText) rootView.findViewById(R.id.text);
        title.setText(note.getTitle());
        text.setText(note.getText());

        ((ButtonPlus) rootView.findViewById(R.id.viewHistoryButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_notes_edit_history);

                TextViewPlus dialogTitle = (TextViewPlus) dialog.findViewById(R.id.title);
                TextViewPlus dialogText = (TextViewPlus) dialog.findViewById(R.id.text);
                TextViewPlus dialogDate = (TextViewPlus) dialog.findViewById(R.id.date);


                NotesHistory prevNote = note.getEditHistories().first();
                if(prevNote==null){
                    dialogTitle.setVisibility(View.GONE);
                    dialogText.setVisibility(View.GONE);
                    dialogDate.setVisibility(View.GONE);

                } else {
                    dialogTitle.setText(prevNote.getTitle());
                    dialogText.setText(prevNote.getText());

                    SimpleDateFormat f = new SimpleDateFormat("dd MMMM yyyy, h:mm a");
                    DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
                    symbols.setAmPmStrings(new String[]{"a.m.", "p.m."});
                    f.setDateFormatSymbols(symbols);

                    dialogDate.setText("Version " + prevNote.getVersion() +
                            ", " + f.format(prevNote.getUpdatedDate()));
                }


                ((ButtonPlus) dialog.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ((ButtonPlus) dialog.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                DialogSize.setSize(EditNotesFragment.this.getActivity(), dialog);
            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesHistory prevNote = new NotesHistory();
                prevNote.setTitle(note.getTitle());
                prevNote.setText(note.getText());
                prevNote.setUpdatedDate(note.getUpdatedDate());
                prevNote.setVersion(note.getVersion());

//              SimpleDateFormat f = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

                Notes newNote = new Notes();
                newNote.setTitle(title.getText().toString().trim());
                newNote.setText(text.getText().toString().trim());
                newNote.setUpdatedDate(Calendar.getInstance().getTime());
                newNote.setVersion(note.getVersion() + 1);
                newNote.getEditHistories().add(prevNote);

                HealthHistory.realm.beginTransaction();
                HealthHistory.realm.copyToRealm(newNote);
                note.removeFromRealm();
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
