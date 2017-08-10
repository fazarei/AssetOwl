package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.domain.event.AnalyticsEventBuilder;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.domain.repository.AnalyticsRepository;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.utils.exception.filter.NetworkExceptionFilter;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by farzanehzarei on 22/3/17.
 */

public class LogoutUseCase extends UseCase {

    private LoginRepository loginRepository;
    private final AnalyticsEventBuilder analyticsEventBuilder;
    private AnalyticsRepository analyticsRepository;

    @Inject
    public LogoutUseCase(AnalyticsEventBuilder analyticsEventBuilder, AnalyticsRepository analyticsRepository, LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
        this.analyticsEventBuilder = analyticsEventBuilder;
        this.analyticsRepository = analyticsRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        loginRepository.logoutFromServer()
                .subscribeOn(Schedulers.io()).subscribe(new Observer<Object>() {

            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                analyticsRepository.push(analyticsEventBuilder.build());
            }

            @Override
            public void onNext(Object o) {
                analyticsRepository.push(analyticsEventBuilder.build());
            }
        });
        return loginRepository.logout();
    }
}
