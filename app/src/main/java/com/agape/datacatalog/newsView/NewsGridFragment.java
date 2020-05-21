package com.agape.datacatalog.newsView;

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

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.NavigationIconClickListener;
import com.agape.datacatalog.R;
import com.agape.datacatalog.packageView.PackageCardRecyclerViewAdapter;
import com.agape.datacatalog.packageView.PackageGridFragment;

public class NewsGridFragment extends Fragment {

    private final String TAG = "MyLOG_NGF";

    private RecyclerView newsRecyclerView;
    private PackageCardRecyclerViewAdapter newsAdapter;
    private Toolbar newsToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---NewsGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_news_grid, container, false);

        setupViews(view);
        setUpToolBar(view);

        return view;
    }

    private void setupViews(View view){
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        newsToolbar = view.findViewById(R.id.news_app_bar);
    }

    private void setUpToolBar(View view){
        AppCompatActivity newsActivity = (AppCompatActivity) getActivity();
        if (newsActivity != null){
            newsActivity.setSupportActionBar(newsToolbar);
        }
        newsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
