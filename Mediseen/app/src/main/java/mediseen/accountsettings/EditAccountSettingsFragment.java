package mediseen.accountsettings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.work.pearlsantos.mediseen.R;


public class EditAccountSettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //my parameters
    SharedPreferences sp;

    //private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditAccountSettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditAccountSettingsFragment newInstance(String param1, String param2) {
        EditAccountSettingsFragment fragment = new EditAccountSettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EditAccountSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_account_settings, container, false);
        sp = getActivity().getSharedPreferences("Mediseen", Context.MODE_PRIVATE);
        //retrieving data from the intent
        String username = getArguments().getString("username");
        String password = getArguments().getString("password");
        String medicalCondition = getArguments().getString("medicalCondition");
        String nameOfSC = getArguments().getString("nameOfSC");
        String nameOfFD = getArguments().getString("nameOfFD");
        String emergencyNum = getArguments().getString("emergencyNum");
        //initializing the text fields
        final EditText userName = (EditText) rootView.findViewById(R.id.userName);
        final EditText passWord = (EditText) rootView.findViewById(R.id.password);
        final EditText medicalCond = (EditText) rootView.findViewById(R.id.medicalCondition);
        final EditText nameOfSeniorCitizen = (EditText) rootView.findViewById(R.id.nameOfSeniorCitizen);
        final EditText nameOfFamilyDoctor = (EditText) rootView.findViewById(R.id.nameOfFamilyDoctor);
        final EditText emergencyNumber = (EditText) rootView.findViewById(R.id.emergencyContactNumber);

        //setting the textfields
        userName.setText(username);
        passWord.setText(password);
        medicalCond.setText(medicalCondition);
        nameOfSeniorCitizen.setText(nameOfSC);
        nameOfFamilyDoctor.setText(nameOfFD);
        emergencyNumber.setText(emergencyNum);


        ((ButtonPlus) rootView.findViewById(R.id.saveAccountChanges)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saving the information into a bundle for sending
                AccountSettings two = new AccountSettings();
                Bundle bundle2 = new Bundle();
                bundle2.putString("username", userName.getText().toString());
                bundle2.putString("password", passWord.getText().toString());
                bundle2.putString("medicalCondition", medicalCond.getText().toString());
                bundle2.putString("nameOfSC", nameOfSeniorCitizen.getText().toString());
                bundle2.putString("nameOfFD", nameOfFamilyDoctor.getText().toString());
                bundle2.putString("emergencyNum", emergencyNumber.getText().toString());
                two.setArguments(bundle2);
                //saving info in a SharedPreferences
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("username", userName.getText().toString());
                ed.putString("password", passWord.getText().toString());
                ed.putString("medicalCondition", medicalCond.getText().toString());
                ed.putString("nameOfSC", nameOfSeniorCitizen.getText().toString());
                ed.putString("nameOfFD", nameOfFamilyDoctor.getText().toString());
                ed.putString("emergencyNum", emergencyNumber.getText().toString());

                ed.commit();

                FragmentReplace.replaceFragment(EditAccountSettingsFragment.this, R.id.content_frame, two);
            }
        });

        ((ButtonPlus) rootView.findViewById(R.id.cancelEditting)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AccountSettings two = new AccountSettings();
                FragmentReplace.replaceFragment(EditAccountSettingsFragment.this, R.id.content_frame, two);
            }
        });



        return rootView;
    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
