package mediseen.helpers;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class CreateNoDisplay {
    public static ViewGroup noDisplay(String alert, ViewGroup wholeLayout, Fragment fragment){
        final Fragment frag = fragment;
        final FrameLayout mainLayout = new FrameLayout(frag.getActivity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mainLayout.setLayoutParams(layoutParams);

        ImageView logo = new ImageView(frag.getActivity());
        Picasso.with(frag.getActivity()).load(R.drawable.logomark).resizeDimen(R.dimen.logoMarkWidth,
                R.dimen.logoMarkHeight).into(logo);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        logo.setLayoutParams(layoutParams2);

        TextViewPlus noThingsText = new TextViewPlus(frag.getActivity());
        noThingsText.setText(alert);
        noThingsText.setGravity(Gravity.START);
        if (Build.VERSION.SDK_INT < 23) {
            noThingsText.setTextAppearance(frag.getActivity(), R.style.bigTextStyle);
        } else {
            noThingsText.setTextAppearance(R.style.bigTextStyle);
        }
        final FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams3.gravity = Gravity.CENTER;
        noThingsText.setLayoutParams(layoutParams3);

        mainLayout.addView(logo);
        mainLayout.addView(noThingsText);

        wholeLayout.addView(mainLayout);
        mainLayout.setVisibility(View.GONE);
        return mainLayout;
    }

//    public static fillNecessaryDetails(ArrayList){
//
//    }
}
