package com.agape.datacatalog.videoView;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.VideoEntry;
import com.agape.datacatalog.network.requester.VideoRequester;

import java.util.List;

public class VideoCardRecyclerViewAdapter extends RecyclerView.Adapter<VideoCardViewHolder> {

    private final String TAG = "MyLOG_VCRVA";
    private MediaController mediaController;
    private int pos;

    private List<VideoEntry> videoList;
    private VideoRequester videoRequester;

    private OnPreparedListener preparedListener;
    private Context context;

    VideoCardRecyclerViewAdapter(List<VideoEntry> videoList, OnPreparedListener preparedListener, Context context){
        this.videoList = videoList;
        this.preparedListener = preparedListener;
        this.context = context;
        videoRequester = videoRequester.getInstance();
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
            VideoEntry product = videoList.get(position);
            holder.videoTitle.setText(product.title);
            holder.videoDescription.setText(product.description);
            videoRequester.setVideoFromRaw(holder.videoView, product.path);

//            mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
//            holder.videoView.setMediaController(mediaController);

//            holder.getView().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
                    preparedListener.playItem(holder.videoView, videoList.get(position).title);

//                    mediaController = new MediaController(context);
//                    mediaController.setAnchorView(holder.videoView);
//                    holder.videoView.setMediaController(mediaController);
//                }
//            });
//            holder.getView().setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    preparedListener.playItem(holder.videoView, videoList.get(position).title);
//                    holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            holder.videoView.seekTo(pos);
//            //                if (pos == 0){
//            //                    videoView.start();
//            //                }
//                            mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                                @Override
//                                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                                    mediaController.setAnchorView(holder.videoView);
//                                }
//                            });
//                        }
//                    });
//                    return false;
//                }
//            });
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
