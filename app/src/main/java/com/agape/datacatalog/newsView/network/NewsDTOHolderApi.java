package com.agape.datacatalog.newsView.network;

import com.agape.datacatalog.newsView.dto.NewsArrDTO;
import com.agape.datacatalog.newsView.dto.NewsResArrDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsDTOHolderApi {
    @GET("get_news_page/100")
    public Call<NewsResArrDTO> getAllNews();

    @GET("news_detail/{pk}/{ip}")
    public Call<NewsArrDTO> getNews(@Path("pk") int pk, @Path("ip") int ip);
}
