package com.assetowl.android.ui.login.observer;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.login.LoginPresenter;
import com.assetowl.domain.utils.exception.AssetOwlException;

import javax.inject.Inject;

/**
 * Created by farzanehzarei on 5/4/17.
 */

public class TermAndConditionAcceptObserver extends DefaultObserver {

    private LoginPresenter loginPresenter;

    @Inject
    TermAndConditionAcceptObserver() {

    }

    public void setLoginPresenter(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof AssetOwlException) {
            switch (((AssetOwlException) e).getErrorCode()) {
                case AssetOwlException.NETWORK_ISSUE:
                    this.loginPresenter.setNetworkError();
                    break;
                case AssetOwlException.NETWORK_TIME_OUT:
                    this.loginPresenter.setNetworkError();
                    break;
                default:
                    this.loginPresenter.setNetworkError();
            }
        } else {
            this.loginPresenter.showError(e.getLocalizedMessage());
        }
    }

    @Override
    public void onNext(Object o) {
        loginPresenter.moveToDashboard();
    }
}
