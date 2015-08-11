package me.drakeet.meizhi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.drakeet.meizhi.R;
import me.drakeet.meizhi.model.Gank;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankListAdapter extends RecyclerView.Adapter<GankListAdapter.ViewHolder> {

    private List<Gank> mGankList;

    public GankListAdapter(List<Gank> gankList) {mGankList = gankList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gank gank = mGankList.get(position);
        if (position == 0) {
            showCategory(holder);
        } else {
            boolean doesLastAndThis = mGankList.get(position-1).type.equals(mGankList.get(position).type);
            if (!doesLastAndThis) showCategory(holder);
            else if (holder.category.isShown()) holder.category.setVisibility(View.GONE);
        }
        holder.category.setText(gank.type);
        holder.gank.setText(gank.desc + "(" + gank.who + ")");
    }

    @Override
    public int getItemCount() {
        return mGankList.size();
    }

    private void showCategory(ViewHolder holder) {
        if (!holder.category.isShown())
            holder.category.setVisibility(View.VISIBLE);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        TextView gank;

        public ViewHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.tv_category);
            gank = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
