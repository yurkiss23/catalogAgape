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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agape.datacatalog.NavigationHost;
import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.NewsEntry;
import com.agape.datacatalog.newsView.click_listener.OnShowListener;
import com.agape.datacatalog.newsView.dto.NewsResArrDTO;
import com.agape.datacatalog.newsView.dto.NewsResDTO;
import com.agape.datacatalog.newsView.network.NewsDTOService;
import com.agape.datacatalog.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsGridFragment extends Fragment implements OnShowListener {

    private final String TAG = "MyLOG_NGF";

    private RecyclerView newsRecyclerView;
    private NewsCardRecyclerViewAdapter newsAdapter;
    private Toolbar newsToolbar;
    private List<NewsEntry> newsEntryList;
    private ProgressBar newsProgressBar;

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
        setRecyclerView();
        loadAllNews();

        return view;
    }

    private void setupViews(View view){
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        newsToolbar = view.findViewById(R.id.news_app_bar);
        newsAdapter = new NewsCardRecyclerViewAdapter(NewsEntry.initNewsEntryList(getResources()),
                this, getContext());
        newsProgressBar = view.findViewById(R.id.pb_loading);
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
        newsEntryList = new ArrayList<>();
        newsAdapter = new NewsCardRecyclerViewAdapter(newsEntryList, this, getContext());
        CommonUtils.setRecyclerView(newsRecyclerView, newsAdapter,
                getActivity(), getResources(), new int[]{1, 3}, "2");

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

    private void loadAllNews(){
        Log.d(TAG, "---loadAllNews---newsEntryList-size = " + newsEntryList.size());
        newsProgressBar.setVisibility(ProgressBar.VISIBLE);
        NewsDTOService.getInstance()
                .getJSONApi()
                .getAllNews()
                .enqueue(new Callback<NewsResArrDTO>() {
                    @Override
                    public void onResponse(Call<NewsResArrDTO> call, Response<NewsResArrDTO> response) {
//                        Toast.makeText(getContext(), "---onResponse---", Toast.LENGTH_LONG).show();
                        if (response.body() != null){
                            newsEntryList.clear();
                            NewsResDTO[] list = response.body().getPopular_news();
                            for (NewsResDTO item : list){
                                NewsEntry newsEntry = new NewsEntry(item.getTitle(), null, null,
                                        NewsDTOService.getNewsUrl()[1] + item.getMain_image(), item.getPk());
                                newsEntryList.add(newsEntry);
                            }
                            newsAdapter.notifyDataSetChanged();
                        }
                        CommonUtils.setProgressBar(newsProgressBar);
                    }

                    @Override
                    public void onFailure(Call<NewsResArrDTO> call, Throwable t) {
                        Toast.makeText(getContext(), "---onFailure---", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showItem(String url) {
        Log.d(TAG, "---showItem---");
        ((NavigationHost)getActivity()).navigateTo(new NewsItemFragment(), true);
    }
}
