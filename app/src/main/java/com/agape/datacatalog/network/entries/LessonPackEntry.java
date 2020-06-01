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

public class LessonPackEntry {
    private static final String TAG = "MyLOG_LPE";

    public final String title;
    public final String description;
    public final String image;
    public final String[] sub;
    public int pk;

    public LessonPackEntry(String title, String description, String image, String[] sub, int pk) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.sub = sub;
        this.pk = pk;
    }

    public static List<LessonPackEntry> initLessonPackEntryList(Resources resources){
        Log.d(TAG, "---initLessonPackEntryList---");
        InputStream inputStream = resources.openRawResource(R.raw.lessons_pack_resources);
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
        String jsonLessonPackString = writer.toString();
        Gson gson = new Gson();
        Type lessonPackListType = new TypeToken<ArrayList<LessonPackEntry>>() {
        }.getType();
        return gson.fromJson(jsonLessonPackString, lessonPackListType);
    }
}
