package com.agape.datacatalog.packageView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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
import android.widget.ToggleButton;

import com.agape.datacatalog.MainActivity;
import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.NavigationIconClickListener;
import com.agape.datacatalog.R;
import com.agape.datacatalog.lessonsView.lessonsPack.LessonsPackFragment;
import com.agape.datacatalog.network.entries.PackageEntry;
import com.agape.datacatalog.newsView.NewsGridFragment;
import com.agape.datacatalog.newsView.NewsItemFragment;
import com.agape.datacatalog.packageView.click_listener.OnShowListener;
import com.agape.datacatalog.utility.CommonUtils;
import com.agape.datacatalog.utility.ListUtils;

public class PackageGridFragment extends Fragment implements OnShowListener {

    private final String TAG = "MyLOG_PGF";

    private Button btnToStart, btnNews, btnLessons, btnVideos, btnCabinet;
    private RecyclerView recyclerView;
    private PackageCardRecyclerViewAdapter packageAdapter;
    private Toolbar toolbar;
    private ProgressBar packageProgressBar;
//    private ToggleButton themeToggleButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.d(TAG, "---PackageGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_package_grid, container, false);

//        initDataLists();

        setupViews(view);
        setUpToolBar(view);
        setRecyclerView(view);

        setBtnNews();
        setBtnLessons();
        setBtnVideos();
        setBtnToStart();
//        setThemeToggleButton();

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
        packageAdapter = new PackageCardRecyclerViewAdapter(PackageEntry.initProductEntryList(getResources()),
                this, getContext());
//        themeToggleButton = view.findViewById(R.id.toggle_theme);
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
//        packageAdapter = new PackageCardRecyclerViewAdapter(PackageEntry.initProductEntryList(getResources()));
        packageAdapter = new PackageCardRecyclerViewAdapter(ListUtils.packageEntryList,
                this, getContext());
        CommonUtils.setRecyclerView(recyclerView, packageAdapter,
                getActivity(), getResources(), new int[]{1, 2}, null);
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

//    private void initDataLists(){
//        ListUtils.initLists();
//    }
//
//    private void loadPackageList(){
//        packageProgressBar.setVisibility(ProgressBar.VISIBLE);
//        packageAdapter.notifyDataSetChanged();
//        CommonUtils.setProgressBar(packageProgressBar);
//    }

    private void setBtnNews(){
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageProgressBar.setVisibility(ProgressBar.VISIBLE);
                CommonUtils.setProgressBar(packageProgressBar);
                Toast.makeText(getContext(), "Роділ в оновлюється", Toast.LENGTH_LONG).show();
//                ((NavigationHost)getActivity()).navigateTo(new NewsGridFragment(), true);
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
                Toast.makeText(getContext(), "Роділ в розробці", Toast.LENGTH_LONG).show();
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

//    private void setThemeToggleButton(){
//        themeToggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getContext(), "---clickToggle---", Toast.LENGTH_SHORT).show();
//                if (themeToggleButton.getBackgroundTintList() == ColorStateList.valueOf(getResources()
//                        .getColor(R.color.colorPrimaryDark, null))){
//                    themeToggleButton.setBackgroundTintList(ColorStateList
//                            .valueOf(getResources().getColor(R.color.tu_colorPrimaryDark, null)));
//                    getContext().setTheme(R.style.TurquoiseTheme);
//                }else {
//                    themeToggleButton.setBackgroundTintList(ColorStateList
//                            .valueOf(getResources().getColor(R.color.colorPrimaryDark, null)));
//                    getContext().setTheme(R.style.AppTheme);
//                }
//            }
//        });
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showItem(int pk) {
        Log.d(TAG, "---showItem---");
        ((NavigationHost)getActivity()).navigateTo(new NewsItemFragment(pk), true);
    }
}
