package mediseen.search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/27/2016.
 */
public class SearchAdapter extends RecyclerView
        .Adapter<SearchAdapter
        .ViewHolder> {
    private ArrayList<SearchObject> mDataset;
    private Context context;
    private Fragment frag;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextViewPlus title, body;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextViewPlus) itemView.findViewById(R.id.title);
            body = (TextViewPlus) itemView.findViewById(R.id.body);
        }
    }

    public SearchAdapter(Context context, ArrayList<SearchObject> myDataset, Fragment frag) {
        this.context = context;
        mDataset = myDataset;
        this.frag = frag;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pill_inventory, parent, false);
        ViewHolder dataObjectHolder = new ViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holderT, final int position) {
        final ViewHolder holder = holderT;
        SearchObject res = mDataset.get(position);
        holder.title.setText(res.getTitle());
        holder.body.setText(res.getBody());
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}