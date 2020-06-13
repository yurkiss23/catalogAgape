package com.agape.datacatalog.network;

import com.agape.datacatalog.newsView.network.NewsDTOService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IpDTOService {
    private static IpDTOService mInstance;
    private static final String BASE_URL = "http://ip-api.com/";
    private Retrofit mRetrofit;

    private IpDTOService(){
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

    public static IpDTOService getInstance(){
        if (mInstance == null){
            mInstance = new IpDTOService();
        }
        return mInstance;
    }

    public IpDTOHolderApi getJSONApi(){return mRetrofit.create(IpDTOHolderApi.class);}
}
