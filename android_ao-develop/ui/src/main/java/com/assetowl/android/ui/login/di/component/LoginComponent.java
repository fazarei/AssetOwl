package com.assetowl.android.ui.login.di.component;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.di.module.ui.AssetOwlActionBarModule;
import com.assetowl.android.di.module.ui.ConnectivityModule;
import com.assetowl.android.mvp.di.component.ActivityComponent;
import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.mvp.di.module.ActivityModule;
import com.assetowl.android.ui.login.LoginActivity;
import com.assetowl.android.ui.login.LoginPresenter;
import com.assetowl.android.ui.login.di.module.LoginModule;

import dagger.Component;

/**
 * Created by jamespott on 7/2/17.
 */

@PerActivity
@Component(dependencies = AssetOwlApplicationComponent.class, modules = {ActivityModule.class, LoginModule.class, AssetOwlActionBarModule.class, ConnectivityModule.class})
public interface LoginComponent extends ActivityComponent {

    void inject(LoginActivity loginActivity);

    LoginPresenter getPresenter();
}
