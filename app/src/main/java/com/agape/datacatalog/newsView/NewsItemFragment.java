package com.agape.datacatalog.newsView;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.agape.datacatalog.R;

public class NewsItemFragment extends Fragment {
    private final String TAG = "MyLOG_NIF";

    private WebView newsView;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "---onCreateView---");
        View view = inflater.inflate(R.layout.fragment_news_item, container, false);

        newsView = view.findViewById(R.id.news_view);
        newsView.getSettings().setJavaScriptEnabled(true);
        WebViewClient webViewClient = new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };

        newsView.setWebViewClient(webViewClient);
        newsView.loadUrl("http://agapeua.com/NewsOpen.html?pk=185");

        return view;
    }
}
