package mediseen.healthhistory;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mediseen.FragmentReplace;
import mediseen.customtextview.ButtonPlus;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.Notes;
import mediseen.pilltracker.inventoryFragments.EditPillsFragment;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 2/23/2016.
 */
public class NotesFragmentAdapter extends RecyclerView
        .Adapter<NotesFragmentAdapter
        .ViewHolder> {
    private RealmResults<Notes> mDataset;
    private Context context;
    private Fragment frag;
    private int pos;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextViewPlus title, date;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.getBackground().clearColorFilter();
            title = (TextViewPlus) itemView.findViewById(R.id.title);
            date = (TextViewPlus) itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            v.invalidate();
            FragmentReplace.replaceFragment(frag, R.id.root_frame, ViewNoteFragment.newInstance(pos));

        }
    }

    public NotesFragmentAdapter(Context context, RealmResults<Notes> myDataset, Fragment frag) {
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
        pos = position;
        final ViewHolder holder = holderT;
        Notes note = mDataset.get(position);
        holder.title.setText(note.getTitle());
        SimpleDateFormat f = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

        holder.date.setText(f.format(note.getUpdatedDate()));

    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}