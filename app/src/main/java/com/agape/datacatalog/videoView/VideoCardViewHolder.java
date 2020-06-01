package com.agape.datacatalog.videoView;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoCardViewHolder extends RecyclerView.ViewHolder {
    private View view;

//    public TextView videoTitle;
//    public TextView videoDescription;
//    YouTubePlayerView youTubePlayerView;
//    public VideoView videoView;
    public WebView webView;
    public VideoCardViewHolder(View view, @NonNull View itemView) {
        super(itemView);
        this.view = itemView;
//        videoTitle = itemView.findViewById(R.id.video_title);
//        videoDescription = itemView.findViewById(R.id.video_description);
        webView = itemView.findViewById(R.id.video_view_frame);
    }

    public View getView() { return view; }
}
