package mediseen.healthhistory;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

import mediseen.helpers.DialogSize;
import mediseen.helpers.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Notes;
import mediseen.database.NotesHistory;
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
        final Notes note = HealthHistory.notes.get(getArguments().getInt(POSITION));
        ((TextViewPlus) rootView.findViewById(R.id.title)).setText(note.getTitle());
        ((TextViewPlus) rootView.findViewById(R.id.text)).setText(note.getText());
        SimpleDateFormat fo = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        ((TextViewPlus) rootView.findViewById(R.id.date)).setText("Last updated " + fo.format(note.getUpdatedDate()));

        final SimpleDateFormat f = new SimpleDateFormat("dd MMMM yyyy, h:mm a");
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        symbols.setAmPmStrings(new String[]{"a.m.", "p.m."});
        f.setDateFormatSymbols(symbols);

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
                if (prevNote == null) {
                    dialogTitle.setVisibility(View.GONE);
                    dialogText.setVisibility(View.GONE);
                    dialogDate.setVisibility(View.GONE);

                } else {
                    dialogTitle.setText(prevNote.getTitle());
                    dialogText.setText(prevNote.getText());

                    dialogDate.setText("Version " + prevNote.getVersion() +
                            ", " + f.format(prevNote.getUpdatedDate()));
                }


//                ((ButtonPlus) dialog.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
                ((ButtonPlus) dialog.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                DialogSize.setSize(ViewNoteFragment.this.getActivity(), dialog);

            }
        });
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

        ((ButtonPlus)rootView.findViewById(R.id.deleteButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete_are_you_sure);
                ((TextViewPlus) dialog.findViewById(R.id.deletingTitle)).setText("DELETING NOTE");
                ((TextViewPlus) dialog.findViewById(R.id.message)).setText("Are you sure you want to delete this note?");
                ((TextViewPlus) dialog.findViewById(R.id.title)).setText(note.getTitle());


                ((TextViewPlus) dialog.findViewById(R.id.additionalInfo)).setText("Version " + note.getVersion() +
                        ", " + f.format(note.getUpdatedDate()));
                ((ButtonPlus) dialog.findViewById(R.id.noButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ((ButtonPlus) dialog.findViewById(R.id.yesButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HealthHistory.realm.beginTransaction();
                        note.removeFromRealm();
                        HealthHistory.realm.commitTransaction();
                        FragmentReplace.replaceFragment(ViewNoteFragment.this, R.id.root_frame, new NotesFragment());
                        dialog.dismiss();
                    }
                });

                dialog.show();


                DialogSize.setSize(ViewNoteFragment.this.getActivity(), dialog);

            }
        });
        return rootView;
    }
}
