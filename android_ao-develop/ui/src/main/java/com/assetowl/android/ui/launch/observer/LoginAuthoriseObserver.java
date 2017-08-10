package com.assetowl.android.ui.launch.observer;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.launch.LaunchPresenter;

import javax.inject.Inject;

import static com.assetowl.domain.login.repository.LoggedInDescriptor.*;

/**
 * Created by farzanehzarei on 22/3/17.
 */

public class LoginAuthoriseObserver extends DefaultObserver<String> {

    private LaunchPresenter launchPresenter;

    @Inject
    LoginAuthoriseObserver() {

    }

    public void setLaunchPresenter(LaunchPresenter launchPresenter) {
        this.launchPresenter = launchPresenter;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(@LoggedInDef String authorise) {
        launchPresenter.verifyLogin(authorise);
    }
}
