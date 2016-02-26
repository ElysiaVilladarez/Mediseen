package mediseen.healthhistory;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class NotesFragmentAdapter extends RecyclerView
        .Adapter<NotesFragmentAdapter
        .ViewHolder> {
    private ArrayList<String> mDataset;
    private Context context;
    private Fragment frag;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.getBackground().clearColorFilter();

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            v.invalidate();
            FragmentTransaction trans = frag.getFragmentManager()
                    .beginTransaction();
				/*
				 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
            trans.replace(R.id.root_frame, new ViewNoteFragment());

				/*
				 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            trans.commit();

        }
    }

    public NotesFragmentAdapter(Context context, ArrayList<String> myDataset, Fragment frag) {
        this.context = context;
        mDataset = myDataset;
        this.frag = frag;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_notes, parent, false);
        ViewHolder dataObjectHolder = new ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holderT, int position) {
        final ViewHolder holder = holderT;

    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}