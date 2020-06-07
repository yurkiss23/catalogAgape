package com.agape.datacatalog.newsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsArrDTO {
    @SerializedName("news")
    @Expose
    private NewsDTO news;

    public NewsDTO getNews() { return news; }

    public void setNews(NewsDTO news) { this.news = news; }

    @Override
    public String toString() {
        return "NewsArrDTO{" +
                "news=" + news +
                '}';
    }
}
