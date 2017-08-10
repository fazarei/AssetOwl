package com.assetowl.android.di.module.ui;

import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.ui.login.observer.ConnectivityCheck;

import dagger.Module;
import dagger.Provides;

/**
 * Created by farzanehzarei on 6/3/17.
 */
@Module
public class ConnectivityModule {

    @Provides
    @PerActivity
    ConnectivityCheck provideConnectivityStatus()
    {
        return new ConnectivityCheck();
    }
}
