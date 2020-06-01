package com.agape.datacatalog.lessonsView.lessonsPack.click_listener;

import android.view.View;

import com.agape.datacatalog.network.entries.LessonPackEntry;

public interface OnClickListener {
    void setOnClick(LessonPackEntry lessonPackEntry, View v);
}
