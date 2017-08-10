package com.assetowl.android.ui.launch;

import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.mvp.presenter.BasePresenter;
import com.assetowl.android.ui.launch.observer.LoginAuthoriseObserver;
import com.assetowl.android.utils.Connectivity;
import com.assetowl.domain.login.repository.LoggedInStatus;
import com.assetowl.domain.login.usecase.VerifyLoggedInUseCase;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by farzanehzarei on 1/3/17.
 */

@PerActivity
public class LaunchPresenter extends BasePresenter<LaunchView> {

    private VerifyLoggedInUseCase verifyLoggedInUseCase;
    private LoginAuthoriseObserver loginAuthoriseObserver;

    @Inject
    public LaunchPresenter(@Named("verifyLogin") UseCase verifyLoggedInUseCase, LoginAuthoriseObserver loginAuthoriseObserver) {
        this.verifyLoggedInUseCase = (VerifyLoggedInUseCase) verifyLoggedInUseCase;
        this.loginAuthoriseObserver = loginAuthoriseObserver;
        this.loginAuthoriseObserver.setLaunchPresenter(this);
    }

    @Override
    public void start() {

        verifyLoggedInUseCase.execute(loginAuthoriseObserver);
    }

    @Override
    public void showError(String message) {
    }

    public void verifyLogin(String status) {
        if (status.equalsIgnoreCase(LoggedInStatus.YES)) {
            getView().launchDashboard();
        } else {
            if (Connectivity.isNetworkAvailable(getView().getContext())) {
                getView().launchLogin();
            }
        }
    }

}
