package com.assetowl.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.assetowl.android.BuildConfig;

/**
 * Created by farzanehzarei on 3/3/17.
 */

public class Connectivity {
    public static boolean isNetworkAvailable(Context context)
    {
        if(BuildConfig.FLAVOR.equals("stub")) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
