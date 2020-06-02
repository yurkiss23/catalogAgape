package com.agape.datacatalog.packageView;

import android.content.Intent;
import android.os.Build;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agape.datacatalog.MainActivity;
import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.NavigationIconClickListener;
import com.agape.datacatalog.R;
import com.agape.datacatalog.lessonsView.LessonsGridFragment;
import com.agape.datacatalog.lessonsView.lessonsPack.LessonsPackFragment;
import com.agape.datacatalog.network.entries.PackageEntry;
import com.agape.datacatalog.newsView.NewsGridFragment;
import com.agape.datacatalog.utility.CommonUtils;
import com.agape.datacatalog.videoView.VideosGridFragment;

public class PackageGridFragment extends Fragment {

    private final String TAG = "MyLOG_PGF";

    private Button btnToStart, btnNews, btnLessons, btnVideos, btnCabinet;
    private RecyclerView recyclerView;
    private PackageCardRecyclerViewAdapter packageAdapter;
    private Toolbar toolbar;
    private ProgressBar packageProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---PackageGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_package_grid, container, false);

        setupViews(view);
        setUpToolBar(view);
        setRecyclerView(view);

        setBtnNews();
        setBtnLessons();
        setBtnVideos();
        setBtnToStart();

        return view;
    }

    private void setupViews(View view){
        btnNews = view.findViewById(R.id.btn_news);
        btnLessons = view.findViewById(R.id.btn_lessons);
        btnVideos = view.findViewById(R.id.btn_videos);
//        btnCabinet = view.findViewById(R.id.btn_cabinet);
        btnToStart = view.findViewById(R.id.btn_home);
        recyclerView = view.findViewById(R.id.recycler_view);
        toolbar = view.findViewById(R.id.package_app_bar);
        packageProgressBar = view.findViewById(R.id.pb_loading);
    }

    private void setUpToolBar(View view){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null){
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.package_grid),
                new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.menu, null),
                getContext().getResources().getDrawable(R.drawable.close, null)));
    }

    private void setRecyclerView(View view){
        packageAdapter = new PackageCardRecyclerViewAdapter(PackageEntry.initProductEntryList(getResources()));
        CommonUtils.setRecyclerView(recyclerView, packageAdapter, getActivity(), getResources(), "package");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
//
//        recyclerView.setAdapter(packageAdapter);
//        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
//        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
//        recyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            view.findViewById(R.id.package_grid).setBackgroundResource(R.drawable.package_grid_background_shape);
        }
    }

    private void setBtnNews(){
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageProgressBar.setVisibility(ProgressBar.VISIBLE);
                CommonUtils.setProgressBar(packageProgressBar);
                ((NavigationHost)getActivity()).navigateTo(new NewsGridFragment(), true);
            }
        });
    }

    private void setBtnLessons(){
        btnLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageProgressBar.setVisibility(ProgressBar.VISIBLE);
                CommonUtils.setProgressBar(packageProgressBar);
                ((NavigationHost)getActivity()).navigateTo(new LessonsPackFragment(), true);
            }
        });
    }

    private void setBtnVideos(){
        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageProgressBar.setVisibility(ProgressBar.VISIBLE);
                CommonUtils.setProgressBar(packageProgressBar);
                Toast.makeText(getContext(), "В розробці", Toast.LENGTH_LONG).show();
//                ((NavigationHost)getActivity()).navigateTo(new VideosGridFragment(), false);
            }
        });
    }

    private void setBtnToStart(){
        btnToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
