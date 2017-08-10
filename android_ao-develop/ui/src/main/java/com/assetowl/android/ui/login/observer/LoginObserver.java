package com.assetowl.android.ui.login.observer;

import android.support.annotation.NonNull;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.login.LoginPresenter;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.utils.exception.AssetOwlException;

import javax.inject.Inject;

import dagger.internal.Preconditions;

/**
 * Created by jamespott on 7/2/17.
 */

public class LoginObserver extends DefaultObserver<SessionInfo> {

    private LoginPresenter loginPresenter;

    @Inject
    LoginObserver() {
    }

    public void setLoginPresenter(@NonNull LoginPresenter loginPresenter) {
        this.loginPresenter = Preconditions.checkNotNull(loginPresenter);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof AssetOwlException) {
            switch (((AssetOwlException) e).getErrorCode()) {
                case AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD:
                    this.loginPresenter.setInvalidUsernameOrPasswordError();
                    break;
                case AssetOwlException.NETWORK_ISSUE:
                case AssetOwlException.NETWORK_TIME_OUT:
                default:
                    this.loginPresenter.setNetworkError();
            }
        } else {
            this.loginPresenter.showError(e.getLocalizedMessage());
        }
    }

    @Override
    public void onNext(SessionInfo sessionInfo) {
        if (sessionInfo.getTosAccepted()) {
            loginPresenter.moveToDashboard();
        } else {
            loginPresenter.termsAndConditionContent();
        }
    }
}
