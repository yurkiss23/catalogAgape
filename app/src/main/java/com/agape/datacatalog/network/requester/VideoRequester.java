package com.agape.datacatalog.network.requester;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.agape.datacatalog.application.CatalogAgape;

public class VideoRequester {
    private final String TAG = "MyLOG_VR";

    private static VideoRequester instance = null;
    private final Context context;
//    private MediaController mediaController;
//    private int position;

    public VideoRequester() {
        context = CatalogAgape.getAppContext();
    }

    public static VideoRequester getInstance(){
        if (instance == null){
            instance = new VideoRequester();
        }
        return instance;
    }

    public void setVideoFromRaw(VideoView videoView, String path){
        Log.d(TAG, "---videoFromRaw---");
        int id = context.getResources().getIdentifier(path, "raw", context.getPackageName());
        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + id);
        videoView.setVideoURI(uri);
    }
}
