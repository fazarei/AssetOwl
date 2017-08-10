package com.assetowl.android.ui.launch.di.component;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.mvp.di.component.ActivityComponent;
import com.assetowl.android.mvp.di.module.ActivityModule;
import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.ui.launch.LaunchActivity;
import com.assetowl.android.ui.launch.LaunchPresenter;
import com.assetowl.android.ui.login.di.module.LoginModule;

import dagger.Component;

/**
 * Created by farzanehzarei on 1/3/17.
 */

@PerActivity
@Component(dependencies = AssetOwlApplicationComponent.class, modules = {ActivityModule.class, LoginModule.class})
public interface LaunchComponent extends ActivityComponent {

    void inject(LaunchActivity launchActivity);

    LaunchPresenter getPresenter();
}
