package com.agape.datacatalog.newsView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agape.datacatalog.R;
import com.android.volley.toolbox.NetworkImageView;

public class NewsCardViewHolder extends RecyclerView.ViewHolder {
    private View view;

    public TextView newsTitle;
    public TextView newsText;
    public NetworkImageView newsImage;
    public NewsCardViewHolder(View view, @NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        newsTitle = itemView.findViewById(R.id.news_title);
        newsText = itemView.findViewById(R.id.news_text);
        newsImage = itemView.findViewById(R.id.news_image);
    }

    public View getView() { return view; }
}
