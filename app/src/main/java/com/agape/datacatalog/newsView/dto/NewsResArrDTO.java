package com.agape.datacatalog.newsView.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class NewsResArrDTO {
    @SerializedName("news_pages")
    @Expose
    private int news_pages;
    @SerializedName("popular_news")
    @Expose
    private NewsResDTO[] popular_news;

    public int getNews_pages() { return news_pages; }

    public void setNews_pages(int news_pages) { this.news_pages = news_pages; }

    public NewsResDTO[] getPopular_news() { return popular_news; }

    public void setPopular_news(NewsResDTO[] popular_news) { this.popular_news = popular_news; }

    @Override
    public String toString() {
        return "NewsResArrDTO{" +
                "news_pages=" + news_pages +
                ", popular_news=" + Arrays.toString(popular_news) +
                '}';
    }
}
