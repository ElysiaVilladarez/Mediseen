package mediseen.healthhistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.customtextview.ButtonPlus;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class AddNotesFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_add_notes, container, false);


        ((ButtonPlus) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
				/*
				 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
                trans.replace(R.id.root_frame, new NotesFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.commit();

            }
        });

        return rootView;
    }
}
