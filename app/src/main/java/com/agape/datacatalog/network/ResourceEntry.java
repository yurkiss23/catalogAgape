package com.agape.datacatalog.network;

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

public class ResourceEntry {

    private static final String TAG = "MyLOG_RE";

    public final String title;
    public final String description;

    public ResourceEntry(String title, String description){
        this.title = title;
        this.description = description;
    }

    public static List<ResourceEntry> initResourcesEntryList(Resources resources) {

        InputStream inputStream = resources.openRawResource(R.raw.resources);
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
        String jsonProductsString = writer.toString();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<ResourceEntry>>() {
        }.getType();
        return gson.fromJson(jsonProductsString, productListType);
    }
}
