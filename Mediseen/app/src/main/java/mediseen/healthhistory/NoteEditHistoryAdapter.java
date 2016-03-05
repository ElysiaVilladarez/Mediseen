package mediseen.healthhistory;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import io.realm.RealmList;
import mediseen.customtextview.TextViewPlus;
import mediseen.database.PillHistory;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/5/2016.
 */
public class NoteEditHistoryAdapter {
    public class PillEditHistoryAdapter extends RecyclerView
            .Adapter<PillEditHistoryAdapter
            .ViewHolder> {
        private RealmList<PillHistory> mDataset;
        private Context context;
        private Fragment frag;
        private int pos;


        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextViewPlus date, time, amountInInventory;
            public ViewHolder(View itemView) {
                super(itemView);
                itemView.getBackground().clearColorFilter();
                amountInInventory = (TextViewPlus) itemView.findViewById(R.id.amountInInventory);
                time = (TextViewPlus) itemView.findViewById(R.id.time);
                date = (TextViewPlus) itemView.findViewById(R.id.date);

            }

        }

        public PillEditHistoryAdapter(Context context, RealmList<PillHistory> myDataset, Fragment frag) {
            this.context = context;
            mDataset = myDataset;
            this.frag = frag;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_pill_history, parent, false);
            ViewHolder dataObjectHolder = new ViewHolder(view);
            return dataObjectHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holderT, int position) {
            pos = position;
            final ViewHolder holder = holderT;
            PillHistory pill = mDataset.get(position);
            holder.date.setText(new SimpleDateFormat("dd MMMM yyyy").format(pill.getCreatedDate()));
            holder.time.setText(new SimpleDateFormat("h:mm a").format(pill.getCreatedDate()));
            holder.amountInInventory.setText(Integer.toString(pill.getAmountInInventory()));

        }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

    }
}
