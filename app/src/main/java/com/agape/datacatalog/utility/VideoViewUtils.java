package com.agape.datacatalog.utility;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;
import android.widget.VideoView;

public final class VideoViewUtils {
    private static final String TAG = "MyLOG_VVU";

    public static void playRawVideo(Context context, VideoView videoView, String resName){
        try {
            int id = VideoViewUtils.getRawResIdByName(context, resName);
            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + id);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
        }catch (Exception e){
            Toast.makeText(context, "error play video: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static int getRawResIdByName(Context context, String resName){
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }
}
