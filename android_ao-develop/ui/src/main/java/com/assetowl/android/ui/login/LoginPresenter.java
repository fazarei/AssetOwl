package com.assetowl.android.ui.login;

import android.support.annotation.NonNull;

import com.assetowl.android.data.login.model.UserDataCredentials;
import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.mvp.presenter.BasePresenter;
import com.assetowl.android.ui.login.observer.ConnectivityCheck;
import com.assetowl.android.ui.login.observer.LastLoggedInUsernameObserver;
import com.assetowl.android.ui.login.observer.LoginObserver;
import com.assetowl.android.ui.login.observer.TermAndConditionAcceptObserver;
import com.assetowl.android.ui.login.observer.TermAndConditionObserver;
import com.assetowl.android.utils.Validators;
import com.assetowl.domain.login.model.UserCredentials;
import com.assetowl.domain.login.usecase.LastLoggedInUsernameUseCase;
import com.assetowl.domain.login.usecase.LoginUseCase;
import com.assetowl.domain.login.usecase.TermAndConditionAcceptUseCase;
import com.assetowl.domain.login.usecase.TermAndConditionUseCase;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscription;

/**
 * Created by jamespott on 31/1/17.
 */

@PerActivity
public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginUseCase loginUseCase;
    private LoginObserver loginObserver;
    private boolean connectionStatus = true;
    private Subscription internetConnectivitySubscription;
    protected ConnectivityCheck connectivityCheck;
    private TermAndConditionUseCase termAndConditionUseCase;
    private TermAndConditionObserver termAndConditionObserver;
    private TermAndConditionAcceptUseCase termAndConditionAcceptUseCase;
    private TermAndConditionAcceptObserver termAndConditionAcceptObserver;
    private LastLoggedInUsernameUseCase lastLoggedInUsernameUseCase;
    private LastLoggedInUsernameObserver lastLoggedInUsernameObserver;

    @Inject
    public LoginPresenter(@Named("login") UseCase loginUseCase, LoginObserver loginObserver, ConnectivityCheck connectivityCheck,
                          @Named("termAndCondition") UseCase termAndConditionUseCase, TermAndConditionObserver termAndConditionObserver,
                          @Named("termAndConditionAccept") UseCase termAndConditionAcceptUseCase, TermAndConditionAcceptObserver termAndConditionAcceptObserver,
                          @Named("lastLoggedInUsername") UseCase lastLoggedInUsernameUseCase, LastLoggedInUsernameObserver lastLoggedInUsernameObserver) {

        this.loginUseCase = (LoginUseCase) loginUseCase;
        this.loginObserver = loginObserver;
        this.loginObserver.setLoginPresenter(this);
        this.connectivityCheck = connectivityCheck;

        this.termAndConditionUseCase = (TermAndConditionUseCase) termAndConditionUseCase;
        this.termAndConditionObserver = termAndConditionObserver;
        this.termAndConditionObserver.setLoginPresenter(this);

        this.termAndConditionAcceptUseCase = (TermAndConditionAcceptUseCase) termAndConditionAcceptUseCase;
        this.termAndConditionAcceptObserver = termAndConditionAcceptObserver;
        this.termAndConditionAcceptObserver.setLoginPresenter(this);

        this.lastLoggedInUsernameUseCase = (LastLoggedInUsernameUseCase) lastLoggedInUsernameUseCase;
        this.lastLoggedInUsernameObserver = lastLoggedInUsernameObserver;
        this.lastLoggedInUsernameObserver.setLoginPresenter(this);
    }

    @Override
    public void start() {
        getView().initialiseView();
        internetConnectivitySubscription = connectivityCheck.ConnectionStatus(this);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void showError(String message) {
        getView().hideProgress();
        getView().showError(message);
    }


    void attemptLogin(String username, String password) {
        clearError();
        if (connectionStatus) {
            if (validateForm(username, password)) {
                login(new UserDataCredentials(username, password));
            }
        } else {
            getView().setConnectivityError();
        }
    }

    private boolean validateForm(@NonNull String username, @NonNull String password) {
        boolean isUsernameValid = false;
        boolean isPasswordValid = false;

        if (!Validators.validateUsername(username)) {
            getView().setUsernameError(true);
            getView().focusOnLastInvalidEntry();
        } else {
            getView().setUsernameError(false);
            isUsernameValid = true;
        }
        if (!Validators.validatePassword(password)) {
            getView().setPasswordError(true);
            getView().focusOnLastInvalidEntry();
        } else {
            getView().setPasswordError(false);
            isPasswordValid = true;
        }
        return isUsernameValid && isPasswordValid;
    }

    private void login(UserCredentials userCredentials) {
        getView().showProgress();
        loginUseCase.setUserCredentials(userCredentials);
        loginUseCase.execute(loginObserver);
    }

    public void termsAndConditionContent() {
        termAndConditionUseCase.execute(termAndConditionObserver);
    }

    public void moveToDashboard() {
        getView().launchDashboard();
    }

    public void showDisconnectMessage(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
        getView().switchToolbar(connectionStatus);
    }

    void safelyUnsubscribe() {
        internetConnectivitySubscription.unsubscribe();
    }

    public void setNetworkError() {
        if(getView() != null) {
            getView().hideProgress();
            getView().setNetworkError();
        }
    }

    public void setInvalidUsernameOrPasswordError() {
        getView().hideProgress();
        getView().setInvalidUsernameOrPasswordError();
    }

    public void clearError() {
        getView().clearError();
    }

    public void showTAndCContent(String title, String body) {
        getView().hideProgress();
        getView().showTAndC(title, body);
    }

    public void tAndCAccept() {
        getView().showProgress();
        termAndConditionAcceptUseCase.execute(termAndConditionAcceptObserver);
    }

    public void declineTAndC() {
        getView().declineTAndC();
    }

    public void setDefaultUsername(String username) {
        getView().setUsername(username);
    }

    public void checkDefaultUsername() {
        lastLoggedInUsernameUseCase.execute(lastLoggedInUsernameObserver);
    }
}
