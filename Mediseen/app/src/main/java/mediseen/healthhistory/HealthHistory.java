package mediseen.healthhistory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import io.realm.RealmResults;
import mediseen.database.Notes;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class HealthHistory extends Fragment {
    public static RealmResults<Notes> notes;
    public static Realm realm;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_root, container, false);
        realm = Realm.getInstance(getActivity());
        notes = realm.allObjects(Notes.class);
        FragmentTransaction trans = getFragmentManager().beginTransaction();

        trans.replace(R.id.root_frame, new NotesFragment());
        trans.commit();
        return rootView;
    }

}
