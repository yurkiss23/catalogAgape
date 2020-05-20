package com.agape.datacatalog.videoView;

import android.widget.TextView;
import android.widget.VideoView;

import com.agape.datacatalog.network.entries.VideoEntry;

public interface OnPreparedListener {
    void playItem(VideoView videoView, String title);
}
