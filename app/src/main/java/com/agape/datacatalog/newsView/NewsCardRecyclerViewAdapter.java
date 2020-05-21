package com.agape.datacatalog.newsView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.NewsEntry;
import com.agape.datacatalog.network.requester.ImageRequester;

import java.util.List;

public class NewsCardRecyclerViewAdapter extends RecyclerView.Adapter<NewsCardViewHolder> {
    private final String TAG = "MyLOG_NCRVA";

    private List<NewsEntry> newsList;
    private ImageRequester imageRequester;

    public NewsCardRecyclerViewAdapter(List<NewsEntry> newsList) {
        this.newsList = newsList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public NewsCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new NewsCardViewHolder(view, view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCardViewHolder holder, int position) {
        Log.d(TAG, "---onBind_newsCard---");

        if (newsList != null && position < newsList.size()){
            NewsEntry news = newsList.get(position);
            holder.newsTitle.setText(news.title);
            holder.newsText.setText(news.text);
            imageRequester.setImageFromUrl(holder.newsImage, news.image);
        }
    }

    @Override
    public int getItemCount() {
        if (newsList != null && newsList.size() > 0){
            return newsList.size();
        }else {
            return 1;
        }
    }
}
