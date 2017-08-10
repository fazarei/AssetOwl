package com.assetowl.domain.login.usecase;

import android.util.Log;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.utils.exception.filter.NetworkExceptionFilter;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.version;

/**
 * Created by farzanehzarei on 4/4/17.
 */

public class TermAndConditionAcceptUseCase extends UseCase {

    private final LoginRepository loginRepository;

    @Inject
    public TermAndConditionAcceptUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
        this.loginRepository = loginRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return loginRepository.termAndConditionAccept().onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<SessionInfo>>());
    }
}
