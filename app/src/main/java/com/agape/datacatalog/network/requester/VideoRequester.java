package com.agape.datacatalog.network.requester;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.VideoView;

import com.agape.datacatalog.application.CatalogAgape;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoRequester extends YouTubeBaseActivity {
    private final String TAG = "MyLOG_VR";

    private static VideoRequester instance = null;
    private final Context context;

    public final String API_KEY = "6D:F2:88:11:A6:98:84:53:02:E1:7B:4F:37:4D:89:D9:D4:EA:6B:A7";

    public VideoRequester() {
        context = CatalogAgape.getAppContext();
    }

    public static VideoRequester getInstance(){
        if (instance == null){
            instance = new VideoRequester();
        }
        return instance;
    }

    public void setVideoFromRaw(WebView webView, String path){
        Log.d(TAG, "---videoFromRaw---");
//        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider,
//                                                YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo(path);
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider,
//                                                YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG).show();
//            }
//        });
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(path);
//        int id = context.getResources().getIdentifier(path, "raw", context.getPackageName());
//        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + id);
//        videoView.setVideoURI(uri);
    }
}
