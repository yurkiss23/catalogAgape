package com.agape.datacatalog.lessonsView.lessonsPack;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agape.datacatalog.R;
import com.agape.datacatalog.utility.CommonUtils;

public class LessonsSubPackFragment extends Fragment {

    private static final String[] ARG_PARAM1 = {};

    private String[] mSubPackList;
    private RecyclerView lessonSubPackRV;
    private LessonPackRecyclerViewAdapter lessonSubPackAdapter;
    private Toolbar lessonSubPackToolbar;

    public LessonsSubPackFragment(String[] subPackList) {
        // Required empty public constructor
    }

    public static LessonsSubPackFragment newInstance(String[] subPackList) {
        LessonsSubPackFragment fragment = new LessonsSubPackFragment(subPackList);
        Bundle args = new Bundle();
        args.putStringArray(String.valueOf(ARG_PARAM1), subPackList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSubPackList = getArguments().getStringArray(String.valueOf(ARG_PARAM1));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons_subpack, container, false);

        setupView(view);
        CommonUtils.setUpToolBar(lessonSubPackToolbar, getActivity());

        return view;
    }

    private void setupView(View view){
        lessonSubPackRV = view.findViewById(R.id.lessons_recycler_view);
        lessonSubPackToolbar = view.findViewById(R.id.lessons_app_bar);
//        lessonSubPackAdapter = new LessonPackRecyclerViewAdapter(LessonPackEntry.initLessonPackEntryList(getResources()),
//                this);
    }
}