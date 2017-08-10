package com.assetowl.android.ui.login.observer;

import android.support.annotation.NonNull;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.mvp.utils.Preconditions;
import com.assetowl.android.ui.login.LoginPresenter;

import javax.inject.Inject;

/**
 * Created by patrickyin on 19/4/17.
 */

public class LastLoggedInUsernameObserver extends DefaultObserver<String> {
    private LoginPresenter loginPresenter;

    @Inject
    LastLoggedInUsernameObserver() {
    }

    public void setLoginPresenter(@NonNull LoginPresenter loginPresenter) {
        this.loginPresenter = Preconditions.checkNotNull(loginPresenter);
    }

    @Override
    public void onNext(String username) {
        loginPresenter.setDefaultUsername(username);
    }

}
