package com.gestion.lastfmapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gestion.lastfmapi.R;
import com.gestion.lastfmapi.models.Artist;
import com.gestion.lastfmapi.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.ViewHolder> {
    private List<Artist> mDataset;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public TopArtistsAdapter(List<Artist> items, Context context, View.OnClickListener onItemClickListener) {
        this.mDataset = items;
        this.mOnClickListener = onItemClickListener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist item = mDataset.get(position);
        ImageLoader.imageLoad(mContext, item.getImageUrl(), R.drawable.ic_empty, holder.artistImageView);
        holder.artistTextView.setText(item.getName());
        holder.listenersTextView.setText(item.getListeners());
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void setDataset(List<Artist> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }

    public Artist getItemByPosition(int position) {
        return mDataset.get(position);
    }

    public void clearDataset() {
        if(mDataset!=null){
            mDataset.clear();
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_artist)
        ImageView artistImageView;
        @BindView(R.id.txt_artist_name)
        TextView artistTextView;
        @BindView(R.id.txt_listeners)
        TextView listenersTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_artist_item)
        void onItemClicked(View view) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(view);
            }
        }
    }

}
