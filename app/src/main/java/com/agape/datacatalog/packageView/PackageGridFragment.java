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

import com.agape.datacatalog.MainActivity;
import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.NavigationIconClickListener;
import com.agape.datacatalog.R;
import com.agape.datacatalog.lessonsView.LessonsGridFragment;
import com.agape.datacatalog.network.PackageEntry;
import com.agape.datacatalog.newsView.NewsGridFragment;
import com.agape.datacatalog.resourcesView.ResourcesGridFragment;

import java.util.Objects;

public class PackageGridFragment extends Fragment {

    private final String TAG = "MyLOG";

    private Button btnToStart, btnNews, btnLessons, btnResources, btnCabinet;
    private RecyclerView recyclerView;
    private PackageCardRecyclerViewAdapter adapter;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        setBtnResources();
        setBtnToStart();

        return view;
    }

    private void setupViews(View view){
        btnNews = view.findViewById(R.id.btn_news);
        btnLessons = view.findViewById(R.id.btn_lessons);
        btnResources = view.findViewById(R.id.btn_resources);
        btnCabinet = view.findViewById(R.id.btn_cabinet);
        btnToStart = view.findViewById(R.id.btn_home);
        recyclerView = view.findViewById(R.id.recycler_view);
        toolbar = view.findViewById(R.id.app_bar);
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

        adapter = new PackageCardRecyclerViewAdapter(PackageEntry.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
        recyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            view.findViewById(R.id.package_grid).setBackgroundResource(R.drawable.package_grid_background_shape);
        }


    }

    private void setBtnNews(){
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new NewsGridFragment(), true);
            }
        });
    }

    private void setBtnLessons(){
        btnLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new LessonsGridFragment(), true);
            }
        });
    }

    private void setBtnResources(){
        btnResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new ResourcesGridFragment(), true);
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
