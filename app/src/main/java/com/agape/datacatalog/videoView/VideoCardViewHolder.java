package com.agape.datacatalog.videoView;

import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;

public class VideoCardViewHolder extends RecyclerView.ViewHolder {
    private View view;

    public TextView videoTitle;
    public TextView videoDescription;
    public VideoView videoView;
    public VideoCardViewHolder(View view, @NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        videoTitle = itemView.findViewById(R.id.video_title);
        videoDescription = itemView.findViewById(R.id.video_description);
        videoView = itemView.findViewById(R.id.video_view_frame);
    }

    public View getView() { return view; }
}
