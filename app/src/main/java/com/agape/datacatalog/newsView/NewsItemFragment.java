package com.agape.datacatalog.newsView;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.IpDTO;
import com.agape.datacatalog.network.IpDTOService;
import com.agape.datacatalog.network.requester.ImageRequester;
import com.agape.datacatalog.newsView.dto.NewsArrDTO;
import com.agape.datacatalog.newsView.dto.NewsDTO;
import com.agape.datacatalog.newsView.network.NewsDTOService;
import com.agape.datacatalog.utility.CommonUtils;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsItemFragment extends Fragment {
    private final String TAG = "MyLOG_NIF";

    private Toolbar newsItemToolbar;
    private ProgressBar newsItemProgressBar;
    private NetworkImageView newsImage;
    private TextView newsTitle;
    private TextView newsText;
    private ImageRequester imageRequester;

    private int pk;

    public NewsItemFragment() {}

    public NewsItemFragment(int pk) {
        this.pk = pk;
        imageRequester = ImageRequester.getInstance();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_news_item, container, false);

        setupViews(view);
        CommonUtils.setUpToolBar(newsItemToolbar, getActivity());
        loadNews();

        return view;
    }

    private void setupViews(View view){
        newsItemToolbar = view.findViewById(R.id.news_app_bar);
        newsItemProgressBar = view.findViewById(R.id.pb_loading);
        newsImage = view.findViewById(R.id.news_image);
        newsTitle = view.findViewById(R.id.news_title);
        newsText = view.findViewById(R.id.news_text);
    }

    private void loadNews(){
        newsItemProgressBar.setVisibility(ProgressBar.VISIBLE);
        getIp();
        CommonUtils.setProgressBar(newsItemProgressBar);
    }

    private void getIp(){
        IpDTOService.getInstance()
                .getJSONApi()
                .getIpDevice()
                .enqueue(new Callback<IpDTO>() {
                    @Override
                    public void onResponse(Call<IpDTO> call, Response<IpDTO> response) {
//                        Toast.makeText(getContext(), TAG + "getIp---onResponse", Toast.LENGTH_SHORT).show();
                        if (response.body() != null){
                            IpDTO resp = response.body();
//                            int ipNum = 0;
                            if (resp.getStatus().equals("success")){
//                                ipNum = Integer.parseInt(resp.getQuery().replaceAll("\\.", ""));
                                getNews(Integer.parseInt(resp.getQuery().replaceAll("\\.", "")));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IpDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "getIp---onFailure", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getNews(int ipNum){
        NewsDTOService.getInstance()
                .getJSONApi()
                .getNews(pk, ipNum)
                .enqueue(new Callback<NewsArrDTO>() {
                    @Override
                    public void onResponse(Call<NewsArrDTO> call, Response<NewsArrDTO> response) {
//                        Toast.makeText(getContext(), TAG + "_onResponse", Toast.LENGTH_SHORT).show();
                        if (response.body() != null){
                            NewsDTO news = response.body().getNews();
                            newsTitle.setText(news.getTitle());
                            newsText.setText(news.getText());
                            imageRequester.setImageFromUrl(newsImage,
                                    NewsDTOService.getNewsUrl()[1] + news.getMain_image());
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsArrDTO> call, Throwable t) {
                        Toast.makeText(getContext(), TAG + "_onFailure", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
