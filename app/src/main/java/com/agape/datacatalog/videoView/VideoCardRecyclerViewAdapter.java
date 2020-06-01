package com.agape.datacatalog.videoView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.VideoEntry;
import com.agape.datacatalog.network.requester.VideoRequester;

import java.util.List;

public class VideoCardRecyclerViewAdapter extends RecyclerView.Adapter<VideoCardViewHolder> {

    private final String TAG = "MyLOG_VCRVA";

    private List<VideoEntry> videoList;
    private VideoRequester videoRequester;

    private OnPreparedListener preparedListener;
    private Context context;

    VideoCardRecyclerViewAdapter(List<VideoEntry> videoList,
                                 OnPreparedListener preparedListener,
                                 Context context){
        this.videoList = videoList;
        this.preparedListener = preparedListener;
        this.context = context;
        videoRequester = VideoRequester.getInstance();
    }
    @NonNull
    @Override
    public VideoCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card, parent, false);
        return new VideoCardViewHolder(view, view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoCardViewHolder holder, int position) {
        Log.d(TAG, "---onBind_videoCard---");

        if (videoList != null && position < videoList.size()) {
            VideoEntry video = videoList.get(position);
//            holder.videoTitle.setText(video.title);
//            holder.videoDescription.setText(video.description);
            videoRequester.setVideoFromRaw(holder.webView, video.path);

            preparedListener.playItem(holder.webView, video.title);
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
