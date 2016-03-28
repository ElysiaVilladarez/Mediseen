package mediseen.generalinfo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mediseen.helpers.URLSpanNoUnderline;
import mediseen.work.pearlsantos.mediseen.R;

public class GeneralInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GeneralInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralInfo newInstance(String param1, String param2) {
        GeneralInfo fragment = new GeneralInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GeneralInfo() {
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
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_general_info, container, false);;
        Picasso.with(getActivity()).load(R.drawable.top).fit().into((ImageView)rootView.findViewById(R.id.topImage));
        Picasso.with(getActivity()).load(R.drawable.tips).fit().into((ImageView)rootView.findViewById(R.id.tipsImage));
        TextView here1 = (TextView)rootView.findViewById(R.id.here1);
        TextView here2 = (TextView)rootView.findViewById(R.id.here2);
        here1.setClickable(true);
        here2.setClickable(true);
        here1.setMovementMethod(LinkMovementMethod.getInstance());
        here2.setMovementMethod(LinkMovementMethod.getInstance());

        Spannable spannedText = Spannable.Factory.getInstance().newSpannable(Html.fromHtml("<a href='http://www.everydayhealth.com/news/most-common-health-concerns-seniors'> here </a>"));
        Spannable processedText = removeUnderlines(spannedText);

        Spannable spannedText2 = Spannable.Factory.getInstance().newSpannable(Html.fromHtml("<a href='http://www.helpguide.org/articles/senior-housing/home-care-services-for-seniors.htm'> here </a>"));
        Spannable processedText2 = removeUnderlines(spannedText2);

        here1.setText(processedText);
        here2.setText(processedText2);


//        here1.setText(Html.fromHtml("<a href='http://www.everydayhealth.com/news/most-common-health-concerns-seniors'> here </a>"));
//        here2.setText(Html.fromHtml("<a href='http://www.helpguide.org/articles/senior-housing/home-care-services-for-seniors.htm'> here </a>"));
        return rootView;
    }

    public static Spannable removeUnderlines(Spannable p_Text) {
        URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);
        for (URLSpan span : spans) {
            int start = p_Text.getSpanStart(span);
            int end = p_Text.getSpanEnd(span);
            p_Text.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            p_Text.setSpan(span, start, end, 0);
        }
        return p_Text;
    }




}
