package com.assetowl.android.ui.dashboard.observer;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.dashboard.DashboardPresenter;
import com.assetowl.domain.login.repository.LoggedInDescriptor.LoggedInDef;
import com.assetowl.domain.login.repository.LoggedInStatus;

import javax.inject.Inject;

/**
 * Created by farzanehzarei on 22/3/17.
 */

public class LogoutObserver extends DefaultObserver<String> {

    private DashboardPresenter dashboardPresenter;
    @Inject
    public LogoutObserver() {
    }

    public void setDashboardPresenter(DashboardPresenter dashboardPresenter) {
        this.dashboardPresenter = dashboardPresenter;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(@LoggedInDef String successLogout) {
        if (successLogout.equalsIgnoreCase(LoggedInStatus.NO))
            dashboardPresenter.logoutSuccess();
    }
}
