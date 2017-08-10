package com.assetowl.android.di.module.ui;

import android.app.Activity;

import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.ui.component.actionbar.ActionBarManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by patrickyin on 1/3/17.
 */
@Module
public class AssetOwlActionBarModule {
    @Provides
    @PerActivity
    ActionBarManager provideActionBarManager(Activity activity) {
        return new ActionBarManager(activity);
    }
}
