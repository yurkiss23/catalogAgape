package com.agape.datacatalog.newsView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.agape.datacatalog.network.entries.NewsEntry;
import com.agape.datacatalog.network.requester.ImageRequester;
import com.agape.datacatalog.newsView.click_listener.OnShowListener;

import java.util.List;

public class NewsCardRecyclerViewAdapter extends RecyclerView.Adapter<NewsCardViewHolder> {
    private final String TAG = "MyLOG_NCRVA";

    private List<NewsEntry> newsList;
    private ImageRequester imageRequester;
    private OnShowListener showListener;
    private Context context;

    public NewsCardRecyclerViewAdapter(List<NewsEntry> newsList,
                                       OnShowListener showListener,
                                       Context context) {
        this.newsList = newsList;
        this.showListener = showListener;
        this.context = context;
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

            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showListener.showItem(news.url);
                }
            });
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
