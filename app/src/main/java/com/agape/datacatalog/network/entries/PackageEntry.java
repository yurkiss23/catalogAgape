package com.agape.datacatalog.network.entries;

import android.content.res.Resources;

import java.util.List;

public class PackageEntry {
    private static final String TAG = "MyLOG_PE";

    public final String title;
    public final String text;
    public final String url;
    public final String image;
    public final int pk;

    public PackageEntry(String title, String text, String url, String image, int pk) {
        this.title = title;
        this.text = text;
        this.url = url;
        this.image = image;
        this.pk = pk;
    }
    public static List<PackageEntry> initProductEntryList(Resources resources) {
        return null;
    }
}
