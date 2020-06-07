package com.agape.datacatalog.newsView.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsDTOService {
    private static NewsDTOService mInstance;
    private static final String BASE_URL = "http://77.120.115.215/agape/api/";
    private static final String MEDIA_URL = BASE_URL + "media/";
    private Retrofit mRetrofit;

    private NewsDTOService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static NewsDTOService getInstance(){
        if (mInstance == null){
            mInstance = new NewsDTOService();
        }
        return mInstance;
    }

    public static String[] getNewsUrl(){
        String[] urls = {BASE_URL, MEDIA_URL};
        return urls;
    }

    public NewsDTOHolderApi getJSONApi(){return mRetrofit.create(NewsDTOHolderApi.class);}
}
