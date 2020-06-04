package com.agape.datacatalog.lessonsView.click_listener;

import android.view.View;

import com.agape.datacatalog.network.entries.LessonPackEntry;

public interface LessonPackOnClickListener {
    void setOnClick(int pk, String title, View v);
}
