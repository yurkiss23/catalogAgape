package com.agape.datacatalog.lessonsView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agape.datacatalog.R;

public class LessonFragment extends Fragment {
    private final String TAG = "MyLOG_LF";

    private Toolbar lessonToolbar;

    private String title;

    public LessonFragment(String title) {
        this.title = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---LessonsFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);

        setupView(view);

        Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://77.120.115.215/agape/api/media/lessons/files/program_1_qOLX4BQ.pdf"));
        startActivity(intent);

        return view;
    }

    private void setupView(View view){
        lessonToolbar = view.findViewById(R.id.lesson_app_bar);
        lessonToolbar.setTitle(title);
    }
}