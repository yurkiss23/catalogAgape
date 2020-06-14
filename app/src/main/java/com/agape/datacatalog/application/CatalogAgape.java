package com.agape.datacatalog.application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.agape.datacatalog.utility.NetworkMonitor;

public class CatalogAgape extends Application {
    private static CatalogAgape instance;
    private static Context appContext;
    private NetworkMonitor mNetworkMonitor;

    public static CatalogAgape getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context mAppContext) {
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        mNetworkMonitor = new NetworkMonitor();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
        registerReceiver(mNetworkMonitor, intentFilter);
    }

    @Override
    public void onTerminate(){
        unregisterReceiver(mNetworkMonitor);
        super.onTerminate();
    }
}
