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

public class NewsEntry {
    private static final String TAG = "MyLOG_NE";

    public final String title;
    public final String text;
    public final String url;
    public final String image;
    public final int pk;

    public NewsEntry(String title, String text, String url, String image, int pk) {
        this.title = title;
        this.text = text;
        this.url = url;
        this.image = image;
        this.pk = pk;
    }

    public static List<NewsEntry> initNewsEntryList(Resources resources){
        Log.d(TAG, "---initNewsEntryList---");
        InputStream inputStream = resources.openRawResource(R.raw.news_resources);
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
        String jsonNewsString = writer.toString();
        Gson gson = new Gson();
        Type newsListType = new TypeToken<ArrayList<NewsEntry>>() {
        }.getType();
        return gson.fromJson(jsonNewsString, newsListType);
    }
}
