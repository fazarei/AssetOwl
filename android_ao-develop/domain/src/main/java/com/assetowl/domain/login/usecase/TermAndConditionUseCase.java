package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.session.model.TermsAndConditionInfo;
import com.assetowl.domain.utils.exception.filter.NetworkExceptionFilter;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by farzanehzarei on 4/4/17.
 */

public class TermAndConditionUseCase extends UseCase<TermsAndConditionInfo> {

    private final LoginRepository loginRepository;
    @Inject
    public TermAndConditionUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
        this.loginRepository = loginRepository;
    }

    @Override
    protected Observable<TermsAndConditionInfo> buildUseCaseObservable() {
        return loginRepository.termAndConditionContent().onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<? extends TermsAndConditionInfo>>());
    }

}
