package com.assetowl.android.ui.dashboard.di.component;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.mvp.di.component.ActivityComponent;
import com.assetowl.android.mvp.di.module.ActivityModule;
import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.ui.dashboard.DashboardPresenter;
import com.assetowl.android.ui.dashboard.di.module.FeatureToggleModule;
import com.assetowl.android.ui.login.di.module.LoginModule;

import dagger.Component;

/**
 * Created by patrickyin on 9/3/17.
 */

@PerActivity
@Component(dependencies = AssetOwlApplicationComponent.class, modules = {ActivityModule.class, LoginModule.class, FeatureToggleModule.class})
public interface DashboardComponent extends ActivityComponent {

    void inject(DashboardActivity dashboardActivity);

    DashboardPresenter getPresenter();
}
