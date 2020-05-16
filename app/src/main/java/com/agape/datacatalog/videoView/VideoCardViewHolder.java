package com.agape.datacatalog.videoView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;

public class VideoCardViewHolder extends RecyclerView.ViewHolder {
    public TextView videoTitle;
    public TextView videoDescription;
    public VideoCardViewHolder(@NonNull View itemView) {
        super(itemView);
        videoTitle = itemView.findViewById(R.id.video_title);
        videoDescription = itemView.findViewById(R.id.video_description);
    }
}
