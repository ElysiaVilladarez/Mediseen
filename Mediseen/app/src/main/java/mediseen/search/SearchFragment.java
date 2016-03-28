package mediseen.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Notes;
import mediseen.database.Pill;
import mediseen.helpers.CreateNoDisplay;
import mediseen.helpers.FragmentReplace;
import mediseen.pilltracker.PillTracker;
import mediseen.pilltracker.inventoryFragments.AddPillsFragment;
import mediseen.pilltracker.inventoryFragments.DividerItemDecoration;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/27/2016.
 */
public class SearchFragment extends Fragment {

    private final static String TEXT = "TEXT";

    public static SearchFragment newInstance(String text) {
        SearchFragment f = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        Realm realm = Realm.getInstance(getActivity().getApplicationContext());
        String query = getArguments().getString(TEXT).trim();

        SpannableStringBuilder builder = new SpannableStringBuilder();

        String normal = "Results for ";
        SpannableString normalSpannable= new SpannableString(normal);
        normalSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), android.R.color.black)), 0, normal.length(), 0);
        builder.append(normalSpannable);

        String blue = query;
        SpannableString blueSpannable= new SpannableString(blue);
        blueSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.backgroundBlue)), 0, blue.length(), 0);
        builder.append(blueSpannable);

        ((TextViewPlus) rootView.findViewById(R.id.resultLabel)).setText(builder, TextView.BufferType.SPANNABLE);

        ArrayList<SearchObject> results = new ArrayList<>();
        RealmResults<Pill> pills;
        if(query.matches("[-+]?\\d*\\.?\\d+")) {
            pills = realm.where(Pill.class).contains("name", query).or().contains("dosage", query).
                    or().equalTo("amountInInventory", Integer.parseInt(query)).or().equalTo("amountTillShopping", Integer.parseInt(query)).or().
                    contains("updatedDate", query).findAll();
        } else{
            pills = realm.where(Pill.class).contains("name", query).or().contains("dosage", query).
                    or().contains("updatedDate", query).findAll();
        }

        RealmResults<Notes> notes = realm.where(Notes.class).contains("title", query).or().contains("text", query).
                or().contains("updatedDate", query).findAll();

        for(Pill p: pills){
            SimpleDateFormat f = new SimpleDateFormat("dd MMMM yyyy, h:mm a");
            DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
            symbols.setAmPmStrings(new String[]{"a.m.", "p.m."});
            f.setDateFormatSymbols(symbols);
            String text = "Dosage: " + p.getDosage() + ", Amount in inventory: " + p.getAmountInInventory() +
                    ", Date Updated: " + f.format(p.getUpdatedDate());
            results.add(new SearchObject(p.getName(), text));
        }

        for(Notes n: notes){
            SimpleDateFormat f = new SimpleDateFormat("dd MMMM yyyy, h:mm a");
            DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
            symbols.setAmPmStrings(new String[]{"a.m.", "p.m."});
            f.setDateFormatSymbols(symbols);
            String text = "";
            if(n.getText().toLowerCase().contains(query.toLowerCase())){
                int where = n.getText().toLowerCase().indexOf(query.toLowerCase());
                int start = 10;
                int end = 10;
                while(where != -1) {
                    if((where - start) < 0){
                        start = 0;
                    } else start = 10;
                    if((where + end) >= n.getText().length()){
                        end = n.getText().length()-1;
                    } else end = 10;

                    text += "..." + n.getText().substring(where-start, where+end) + "...";
                }
            }
            results.add(new SearchObject(n.getTitle(), text));
        }

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.searchResults);
        LinearLayout mf = (LinearLayout) rootView.findViewById(R.id.mainFrame);
        FrameLayout whole = (FrameLayout) rootView.findViewById(R.id.whole);
        if(results.isEmpty()){
            mf.setVisibility(View.GONE);
            String noMatch = "Sorry, no item\nmatch '" + query + "'.";
            CreateNoDisplay.noDisplay(noMatch, whole, this).setVisibility(View.VISIBLE);
        } else {
            mf.setVisibility(View.VISIBLE);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            SearchAdapter adapter = new SearchAdapter(getActivity(), results, this);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }

        return rootView;
    }
}
