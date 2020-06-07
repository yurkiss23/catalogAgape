package com.agape.datacatalog.newsView;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.agape.datacatalog.R;
import com.agape.datacatalog.utility.CommonUtils;

public class NewsItemFragment extends Fragment {
    private final String TAG = "MyLOG_NIF";

    private Toolbar newsItemToolbar;
    private ProgressBar newsItemProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_news_item, container, false);

        setupViews(view);
        CommonUtils.setUpToolBar(newsItemToolbar, getActivity());
        newsItemProgressBar.setVisibility(ProgressBar.VISIBLE);
        CommonUtils.setProgressBar(newsItemProgressBar);

        return view;
    }

    private void setupViews(View view){
        newsItemToolbar = view.findViewById(R.id.news_app_bar);
        newsItemProgressBar = view.findViewById(R.id.pb_loading);
    }
}
