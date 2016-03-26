package mediseen.accountsettings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/14/2016.
 */
public class ViewAccount extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_account, container, false);
        super.onCreate(savedInstanceState);


        return rootView;
    }
}
