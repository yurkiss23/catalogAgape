package com.agape.datacatalog.lessonsView;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.LessonEntry;
import com.agape.datacatalog.packageView.PackageGridFragment;
import com.agape.datacatalog.packageView.PackageGridItemDecoration;

public class LessonsGridFragment extends Fragment {
    private final String TAG = "MyLOG_LGF";

    private RecyclerView lessonRecyclerView;
    private LessonCardRecyclerViewAdapter lessonAdapter;
    private Toolbar lessonToolbar;

    private int lSpanCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---LessonsGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_lessons_grid, container, false);

        setupView(view);
        setUpToolBar();
        setRecyclerView();

        return view;
    }

    private void setupView(View view){
        lessonRecyclerView = view.findViewById(R.id.lessons_recycler_view);
        lessonToolbar = view.findViewById(R.id.lessons_app_bar);
    }

    private void setUpToolBar(){
        AppCompatActivity lessonActivity = (AppCompatActivity) getActivity();
        if (lessonActivity != null){
            lessonActivity.setSupportActionBar(lessonToolbar);
        }
        lessonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
            }
        });
    }

    private void setRecyclerView(){
        lessonRecyclerView.setHasFixedSize(true);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            lSpanCount = 2;
        }else {
            lSpanCount = 3;
        }
        lessonRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                lSpanCount, GridLayoutManager.VERTICAL, false));
        lessonAdapter = new LessonCardRecyclerViewAdapter(LessonEntry.initLessonEntryList(getResources()));
        lessonRecyclerView.setAdapter(lessonAdapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
        lessonRecyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
