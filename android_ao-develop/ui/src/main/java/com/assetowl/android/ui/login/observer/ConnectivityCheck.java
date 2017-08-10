package com.assetowl.android.ui.login.observer;

import com.assetowl.android.BuildConfig;
import com.assetowl.mvp.presenter.Presenter;
import com.assetowl.android.ui.login.LoginPresenter;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import java.util.Observable;

import dagger.internal.Preconditions;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by farzanehzarei on 6/3/17.
 */

public class ConnectivityCheck extends Observable {
    Presenter loginPresenter;

    public Subscription ConnectionStatus(LoginPresenter loginPresenter) {
        this.loginPresenter = Preconditions.checkNotNull(loginPresenter);
        return ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isConnectedToInternet) {
                        if(!BuildConfig.FLAVOR.equals("stub")) {
                            loginPresenter.showDisconnectMessage(isConnectedToInternet);
                        }
                    }

                });
    }

}