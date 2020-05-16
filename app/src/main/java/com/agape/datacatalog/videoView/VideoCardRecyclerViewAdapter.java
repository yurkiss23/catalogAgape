package com.agape.datacatalog.videoView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.VideoEntry;

import java.util.List;

public class VideoCardRecyclerViewAdapter extends RecyclerView.Adapter<VideoCardViewHolder> {

    private List<VideoEntry> videoList;

    VideoCardRecyclerViewAdapter(List<VideoEntry>videoList){
        this.videoList = videoList;
    }
    @NonNull
    @Override
    public VideoCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card, parent, false);
        return new VideoCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoCardViewHolder holder, int position) {
        if (videoList != null && position < videoList.size()) {
            VideoEntry product = videoList.get(position);
            holder.videoTitle.setText(product.title);
            holder.videoDescription.setText(product.description);
        }
    }

    @Override
    public int getItemCount() {
        if (videoList != null && videoList.size() > 0){
            return  videoList.size();
        }else {
            return 1;
        }
    }
}
