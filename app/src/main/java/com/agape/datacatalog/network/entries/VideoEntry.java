package com.agape.datacatalog.network.entries;

import android.content.res.Resources;
import android.util.Log;

import com.agape.datacatalog.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VideoEntry {

    private static final String TAG = "MyLOG_VE";

    public final String title;
    public final String description;
    public final String path;

    public VideoEntry(String title, String description, String path){
        this.title = title;
        this.description = description;
        this.path = path;
    }

    public static List<VideoEntry> initVideosEntryList(Resources resources) {
        Log.d(TAG, "---initVideosEntryList---");
        InputStream inputStream = resources.openRawResource(R.raw.video_resources);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error closing the input stream.", exception);
            }
        }
        String jsonVideosString = writer.toString();
        Gson gson = new Gson();
        Type videoListType = new TypeToken<ArrayList<VideoEntry>>() {
        }.getType();
        return gson.fromJson(jsonVideosString, videoListType);
    }
}
