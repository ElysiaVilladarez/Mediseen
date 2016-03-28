package mediseen.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mediseen.work.pearlsantos.mediseen.R;

public class Greeting extends Fragment {
    public Greeting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_greeting, container, false);
        Picasso.with(getActivity()).load(R.drawable.home).fit().into((ImageView)rootView.findViewById(R.id.homeView));
        return rootView;
    }
}
