package com.agape.datacatalog.lessonsView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agape.datacatalog.R;

public class LessonFragment extends Fragment {

    public static LessonFragment newInstance(String param1, String param2) {
        LessonFragment fragment = new LessonFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);



        return view;
    }
}