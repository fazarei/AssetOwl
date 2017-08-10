package com.assetowl.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.assetowl.android.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by farzanehzarei on 9/3/17.
 */

@RunWith(PowerMockRunner.class)
public class ConnectivityTest {
    @Mock
    Context context;
    @Mock
    ConnectivityManager connectivityManager;

    @Test
    public void checkConnectivityManager() throws Exception {
        if(!BuildConfig.FLAVOR.equals("stub")) {
            when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
            Connectivity.isNetworkAvailable(context);

            Mockito.verify(context).getSystemService(Context.CONNECTIVITY_SERVICE);
            Mockito.verify(connectivityManager).getActiveNetworkInfo();
        }
    }

}
