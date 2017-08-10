package com.assetowl.android.ui.login.observer;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.login.LoginPresenter;
import com.assetowl.domain.session.model.TermsAndConditionInfo;
import com.assetowl.domain.utils.exception.AssetOwlException;

import javax.inject.Inject;

/**
 * Created by farzanehzarei on 4/4/17.
 */

public class TermAndConditionObserver extends DefaultObserver<TermsAndConditionInfo> {

    private LoginPresenter loginPresenter;
    @Inject
    TermAndConditionObserver() {}

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
    public void onNext(TermsAndConditionInfo termsAndConditionInfo) {
        loginPresenter.showTAndCContent(termsAndConditionInfo.getTitle(),termsAndConditionInfo.getBody());
    }
}
