package com.agape.datacatalog.videoView;

import android.webkit.WebView;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubePlayerView;

public interface OnPreparedListener {
    void playItem(WebView webView, String title);
}
