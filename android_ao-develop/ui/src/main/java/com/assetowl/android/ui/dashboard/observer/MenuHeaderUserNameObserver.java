package com.assetowl.android.ui.dashboard.observer;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.dashboard.DashboardPresenter;

import javax.inject.Inject;

/**
 * Created by farzanehzarei on 25/5/17.
 */

public class MenuHeaderUserNameObserver extends DefaultObserver<String> {

    private DashboardPresenter dashboardPresenter;

    @Inject
    MenuHeaderUserNameObserver() {
    }

    public void setDashboardPresenter(DashboardPresenter dashboardPresenter) {
        this.dashboardPresenter = dashboardPresenter;
    }

    @Override
    public void onNext(String username) {
        dashboardPresenter.setUsernameInHeader(username);
    }
}
