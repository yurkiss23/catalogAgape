package com.agape.datacatalog.lessonsView.lessonsPack.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LessonDTOService {
    private static LessonDTOService mInstance;
    private static final String BASE_URL = "http://77.120.115.215/agape/api/";
    private Retrofit mRetrofit;

    private LessonDTOService(){
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

    public static LessonDTOService getInstance(){
        if (mInstance == null){
            mInstance = new LessonDTOService();
        }
        return mInstance;
    }

    public LessonDTOHolderApi getJSONApi(){return mRetrofit.create(LessonDTOHolderApi.class);}
}
