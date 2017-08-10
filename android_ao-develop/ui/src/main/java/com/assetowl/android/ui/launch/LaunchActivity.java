package com.assetowl.android.ui.launch;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.R;
import com.assetowl.android.mvp.ui.PresenterActivity;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.ui.launch.di.component.LaunchComponent;
import com.assetowl.android.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by farzanehzarei on 1/3/17.
 */

public class LaunchActivity extends PresenterActivity<LaunchPresenter> implements LaunchView {

    private LaunchComponent splashComponent;

    @Override
    protected void initialiseInjector() {
        final AssetOwlApplication application = (AssetOwlApplication) getApplicationContext();
        splashComponent = application.getComponentFactory().createLaunchComponent(getActivityModule());
        splashComponent.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_launch;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_dark_color));
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void launchLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void launchDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.button_exit)
    public void exitApp() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
