package com.agape.datacatalog.newsView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.NewsEntry;
import com.agape.datacatalog.utility.CommonUtils;

public class NewsGridFragment extends Fragment implements OnShowListener {

    private final String TAG = "MyLOG_NGF";

    private RecyclerView newsRecyclerView;
    private NewsCardRecyclerViewAdapter newsAdapter;
    private Toolbar newsToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---NewsGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_news_grid, container, false);

        setupViews(view);
        CommonUtils.setUpToolBar(newsToolbar, getActivity());
        CommonUtils.setRecyclerView(newsRecyclerView, newsAdapter,
                getActivity(), getResources(), null, null);

        return view;
    }

    private void setupViews(View view){
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        newsToolbar = view.findViewById(R.id.news_app_bar);
        newsAdapter = new NewsCardRecyclerViewAdapter(NewsEntry.initNewsEntryList(getResources()),
                this, getContext());
    }

//    private void setUpToolBar(){
//        AppCompatActivity newsActivity = (AppCompatActivity) getActivity();
//        if (newsActivity != null){
//            newsActivity.setSupportActionBar(newsToolbar);
//        }
//        newsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((NavigationHost)getActivity()).navigateTo(new PackageGridFragment(), false);
//            }
//        });
//    }
//
//    private void loadRecyclerView(){
//        Log.d(TAG, "---recyclerView---");
//
//        newsRecyclerView.setHasFixedSize(true);
//        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            nSpanCount = 2;
//        }else {
//            nSpanCount = 3;
//        }
//        newsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
//                nSpanCount, GridLayoutManager.VERTICAL, false));
//        newsAdapter = new NewsCardRecyclerViewAdapter(NewsEntry.initNewsEntryList(getResources()));
//        newsRecyclerView.setAdapter(newsAdapter);
//        int largePadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing);
//        int smallPadding = getResources().getDimensionPixelSize(R.dimen.package_grid_spacing_small);
//        newsRecyclerView.addItemDecoration(new PackageGridItemDecoration(largePadding, smallPadding));
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showItem(String url) {
        Log.d(TAG, "---showItem---");
        ((NavigationHost)getActivity()).navigateTo(new NewsItemFragment(), false);
    }
}
