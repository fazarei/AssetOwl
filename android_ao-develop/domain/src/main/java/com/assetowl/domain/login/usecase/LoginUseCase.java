package com.assetowl.domain.login.usecase;

import android.support.annotation.NonNull;

import com.assetowl.android.mvp.domain.event.AnalyticsEventBuilder;
import com.assetowl.android.mvp.domain.repository.AnalyticsRepository;
import com.assetowl.domain.login.analytics.builder.LoginAnalyticsEventBuilder;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.model.UserCredentials;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.utils.exception.filter.NetworkExceptionFilter;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * Created by jamespott on 7/2/17.
 */

public class LoginUseCase extends UseCase<SessionInfo> {

    private UserCredentials userCredentials;
    private AnalyticsRepository analyticsRepository;
    private final LoginRepository loginRepository;
    private final AnalyticsEventBuilder analyticsEventBuilder;

    @Inject
    public LoginUseCase(AnalyticsEventBuilder analyticsEventBuilder, AnalyticsRepository analyticsRepository, LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
        this.analyticsEventBuilder = analyticsEventBuilder;
        this.analyticsRepository = analyticsRepository;
        this.loginRepository = loginRepository;
    }

    /**
     * Executed after user credentials are set
     *
     * @return Observable containing {@link SessionInfo}
     */
    @Override
    protected Observable<SessionInfo> buildUseCaseObservable() {
        ReplaySubject<SessionInfo> loginUseCaseObservable = ReplaySubject.create();
        loginRepository.login(userCredentials).onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<? extends SessionInfo>>())
                .subscribeOn(Schedulers.io())
                .subscribe(loginUseCaseObservable);
        loginUseCaseObservable.forEach(new Action1<SessionInfo>() {
            @Override
            public void call(SessionInfo sessionInfo) {
                analyticsRepository.push(analyticsEventBuilder.add("email", sessionInfo.getEmail()).build());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });

        return loginUseCaseObservable;
    }


    public void setUserCredentials(@NonNull UserCredentials userCredentials) {
        this.userCredentials = Preconditions.checkNotNull(userCredentials);
    }
}
