package com.agape.datacatalog.newsView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.NewsEntry;
import com.agape.datacatalog.newsView.click_listener.OnShowListener;
import com.agape.datacatalog.utility.CommonUtils;
import com.agape.datacatalog.utility.ListUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewsGridFragment extends Fragment implements OnShowListener {

    private final String TAG = "MyLOG_NGF";

    private RecyclerView newsRecyclerView;
    private NewsCardRecyclerViewAdapter newsAdapter;
    private Toolbar newsToolbar;
//    private List<NewsEntry> newsEntryList;
    private ProgressBar newsProgressBar;
    private FloatingActionButton newsFab;
    private NestedScrollView newsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        Log.d(TAG, "---NewsGridFragment---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_news_grid, container, false);

        setupViews(view);
        CommonUtils.setUpToolBar(newsToolbar, getActivity());
        setRecyclerView();
        loadNewsList();
//        loadAllNews();

        return view;
    }

    private void setupViews(View view){
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        newsToolbar = view.findViewById(R.id.news_app_bar);
        newsAdapter = new NewsCardRecyclerViewAdapter(NewsEntry.initNewsEntryList(getResources()),
                this, getContext());
        newsProgressBar = view.findViewById(R.id.pb_loading);
        newsFab = view.findViewById(R.id.floating_action_button);
        newsView = view.findViewById(R.id.news_grid);
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

    private void setRecyclerView(){
        Log.d(TAG, "---recyclerView---");
//        newsEntryList = new ArrayList<>();
        newsAdapter = new NewsCardRecyclerViewAdapter(ListUtils.newsEntryList, this, getContext());
        CommonUtils.setRecyclerView(newsRecyclerView, newsAdapter,
                getActivity(), getResources(), new int[]{1, 2}, "2");
//        newsFab.show();
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
    }

    private void loadNewsList(){
        newsProgressBar.setVisibility(ProgressBar.VISIBLE);
        newsAdapter.notifyDataSetChanged();
        CommonUtils.setProgressBar(newsProgressBar);
        CommonUtils.setOnScrollGrid(newsView, newsFab);
    }

//    private void setOnScrollNewsGrid(){
//        newsView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
////                Toast.makeText(getContext(), TAG + "scroll" + scrollX + " " + scrollY + " " + oldScrollX + " " + oldScrollY, Toast.LENGTH_LONG).show();
//                setOnClickNewsFAB();
//                if (scrollY > 200){
//                    newsFab.show();
//                }else {
//                    newsFab.hide();
//                }
//            }
//        });
//    }
//
//    private void setOnClickNewsFAB(){
//        newsFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getContext(), TAG + "to_top", Toast.LENGTH_SHORT).show();
//                newsView.fullScroll(ScrollView.FOCUS_UP);
//            }
//        });
//    }

//    private void loadAllNews(){
//        Log.d(TAG, "---loadAllNews---newsEntryList-size = " + newsEntryList.size());
//        newsProgressBar.setVisibility(ProgressBar.VISIBLE);
//        NewsDTOService.getInstance()
//                .getJSONApi()
//                .getAllNews()
//                .enqueue(new Callback<NewsResArrDTO>() {
//                    @Override
//                    public void onResponse(Call<NewsResArrDTO> call, Response<NewsResArrDTO> response) {
////                        Toast.makeText(getContext(), "---onResponse---", Toast.LENGTH_LONG).show();
//                        if (response.body() != null){
//                            newsEntryList.clear();
//                            NewsResDTO[] list = response.body().getPopular_news();
//                            for (NewsResDTO item : list){
//                                NewsEntry newsEntry = new NewsEntry(item.getTitle(), null, null,
//                                        NewsDTOService.getNewsUrl()[1] + item.getMain_image(), item.getPk());
//                                newsEntryList.add(newsEntry);
//                            }
//                            newsAdapter.notifyDataSetChanged();
//                        }
//                        CommonUtils.setProgressBar(newsProgressBar);
//                    }
//
//                    @Override
//                    public void onFailure(Call<NewsResArrDTO> call, Throwable t) {
//                        Toast.makeText(getContext(), "---onFailure---", Toast.LENGTH_LONG).show();
//                    }
//                });
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
