package com.agape.datacatalog.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetworkMonitor extends BroadcastReceiver {
    private final String TAG = "MyLOG_NM";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, action);
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        Log.d(TAG, "isConnected: " + isConnected);
        if (!isConnected){return;}
        boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        Log.d(TAG, "Wifi: " + isWifi);
        if (!isWifi){return;}
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        Log.d(TAG, connectionInfo.getSSID());
    }
}
