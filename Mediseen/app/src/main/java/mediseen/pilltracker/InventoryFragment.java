package mediseen.pilltracker;

/**
 * Created by elysi on 2/20/2016.
 */

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.CustomFontHelper;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Pill;
import mediseen.work.pearlsantos.mediseen.R;

public class InventoryFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "POSITION";
    private final String TEXT_FONT = "fonts/Signika-Regular_0.ttf";

    public static InventoryFragment newInstance(int position) {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public InventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);
        final ButtonPlus addPillsButton = (ButtonPlus) rootView.findViewById(R.id.addPillsButton);
        final LinearLayout addingPillsLayout = (LinearLayout) rootView.findViewById(R.id.addingPillsLayout);
        final FrameLayout pillInventoryLayout = (FrameLayout) rootView.findViewById(R.id.pillInventoryLayout);

        if (true) {
            FrameLayout wholeFrame = (FrameLayout) rootView.findViewById(R.id.wholeFrame);

            final FrameLayout noPillsLayout = new FrameLayout(getActivity());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            noPillsLayout.setLayoutParams(layoutParams);

            ImageView logo = new ImageView(getActivity());
            Picasso.with(getActivity()).load(R.drawable.logomark).resizeDimen(R.dimen.logoMarkWidth,
                    R.dimen.logoMarkHeight).into(logo);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams2.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            logo.setLayoutParams(layoutParams2);

            TextViewPlus noPillsText = new TextViewPlus(getActivity());
            noPillsText.setText(R.string.noPills);
            noPillsText.setGravity(Gravity.START);
            if (Build.VERSION.SDK_INT < 23) {
                noPillsText.setTextAppearance(getActivity(), R.style.bigTextStyle);
            } else {
                noPillsText.setTextAppearance(R.style.bigTextStyle);
            }
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams3.gravity = Gravity.CENTER;
            noPillsText.setLayoutParams(layoutParams3);

            noPillsLayout.addView(logo);
            noPillsLayout.addView(noPillsText);

            wholeFrame.addView(noPillsLayout);

            addPillsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChangeVisibilityHelper.changeVisibility(addingPillsLayout, noPillsLayout);
                    addPillsButton.setVisibility(View.GONE);
                }
            });
        }

        ((ButtonPlus) rootView.findViewById(R.id.cancelButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeVisibilityHelper.changeVisibility(pillInventoryLayout, addingPillsLayout);
            }
        });
        ((ButtonPlus) rootView.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeVisibilityHelper.changeVisibility(pillInventoryLayout, addingPillsLayout);
            }
        });

        ArrayList<String> lol = new ArrayList<>();
        lol.add("MEDICINE1");
        lol.add("MEDICINE2");
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.pillInventory);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        InventoryAdapter adapter = new InventoryAdapter(getActivity(), lol);
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }
}