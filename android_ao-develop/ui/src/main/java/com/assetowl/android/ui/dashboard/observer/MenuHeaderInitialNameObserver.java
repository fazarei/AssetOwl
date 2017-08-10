package com.assetowl.android.ui.dashboard.observer;

import android.util.Log;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.dashboard.DashboardPresenter;

import javax.inject.Inject;

/**
 * Created by farzanehzarei on 1/6/17.
 */

public class MenuHeaderInitialNameObserver extends DefaultObserver<String> {

    private DashboardPresenter dashboardPresenter;

    @Inject
    MenuHeaderInitialNameObserver() {
    }

    public void setDashboardPresenter(DashboardPresenter dashboardPresenter) {
        this.dashboardPresenter = dashboardPresenter;
    }

    @Override
    public void onNext(String initialiseName) {
        dashboardPresenter.setInitialiseName(initialiseName);
    }
}
