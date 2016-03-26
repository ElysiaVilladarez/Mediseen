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
import android.widget.TextView;

import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.work.pearlsantos.mediseen.R;


public class AccountSettings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //my field
    SharedPreferences sp;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountSettings newInstance(String param1, String param2) {
        AccountSettings fragment = new AccountSettings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AccountSettings() {
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
        final View rootView = inflater.inflate(R.layout.fragment_account_settings, container, false);


        //initializing the text fields
        TextView userName = (TextView)rootView.findViewById(R.id.username);
        TextView passWord = (TextView)rootView.findViewById(R.id.password);
        TextView medicalCond = (TextView)rootView.findViewById(R.id.medicalCondition);
        TextView nameOfSenCit = (TextView)rootView.findViewById(R.id.seniorCitizenName);
        TextView nameOfFamDoc = (TextView)rootView.findViewById(R.id.doctorName);
        TextView emergencyNumber = (TextView)rootView.findViewById(R.id.contactNum);

        String username;
        String password;
        String medicalCondition;
        String nameOfSC;
        String nameOfFD;
        String emergencyNum;

        if(getArguments() != null){
            username = getArguments().getString("username");
            password = getArguments().getString("password");
            medicalCondition = getArguments().getString("medicalCondition");
            nameOfSC = getArguments().getString("nameOfSC");
            nameOfFD = getArguments().getString("nameOfFD");
            emergencyNum = getArguments().getString("emergencyNum");


        }
        else{
            // TO DO: change the default values into <No specified chorva> when the onFirstAccess account settings is implemented
            sp = getActivity().getSharedPreferences("Mediseen", Context.MODE_PRIVATE);
            username = sp.getString("username", userName.getText().toString().trim());
            password = sp.getString("password", passWord.getText().toString().trim());
            medicalCondition = sp.getString("medicalCondition", medicalCond.getText().toString().trim());
            nameOfSC = sp.getString("nameOfSC", nameOfFamDoc.getText().toString().trim());
            nameOfFD = sp.getString("nameOfFD", nameOfSenCit.getText().toString().trim());
            emergencyNum = sp.getString("emergencyNum", emergencyNumber.getText().toString().trim());

        }

        userName.setText(username);
        passWord.setText(password);
        medicalCond.setText(medicalCondition);
        nameOfSenCit.setText(nameOfSC);
        nameOfFamDoc.setText(nameOfFD);
        emergencyNumber.setText(emergencyNum);





        ((ButtonPlus) rootView.findViewById(R.id.accountEditButton)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView username = (TextView)rootView.findViewById(R.id.username);
                TextView password = (TextView)rootView.findViewById(R.id.password);
                TextView medicalCondition = (TextView)rootView.findViewById(R.id.medicalCondition);
                TextView nameOfSC = (TextView)rootView.findViewById(R.id.seniorCitizenName);
                TextView nameOfFD = (TextView)rootView.findViewById(R.id.doctorName);
                TextView emergencyNum = (TextView)rootView.findViewById(R.id.contactNum);
//                Intent intent = new Intent(getActivity(), EditAccountSettingsFragment.class);
//                intent.putExtra("username", username.getText().toString());
//                intent.putExtra("password", password.getText().toString());
//                intent.putExtra("medicalCondition", medicalCondition.getText().toString());
//                intent.putExtra("nameOfSC", nameOfSC.getText().toString());
//                intent.putExtra("nameOfFD", nameOfFD.getText().toString());
//                intent.putExtra("emergencyNum", emergencyNum.getText().toString());
//
//                getActivity().startActivity(intent);
//                getActivity().finish();

                EditAccountSettingsFragment two = new EditAccountSettingsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("username", username.getText().toString());
                bundle.putString("password", password.getText().toString());
                bundle.putString("medicalCondition", medicalCondition.getText().toString());
                bundle.putString("nameOfSC", nameOfSC.getText().toString());
                bundle.putString("nameOfFD", nameOfFD.getText().toString());
                bundle.putString("emergencyNum", emergencyNum.getText().toString());
                two.setArguments(bundle);

                FragmentReplace.replaceFragment(AccountSettings.this, R.id.content_frame, two);
            }
        });

        return rootView;
    }

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
