package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by farzanehzarei on 22/3/17.
 */

public class VerifyLoggedInUseCase extends UseCase<String> {

    private final LoginRepository loginRepository;

    @Inject
    public VerifyLoggedInUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
        this.loginRepository = loginRepository;
    }

    @Override
    protected Observable<String> buildUseCaseObservable() {
        return loginRepository.hasUserLoggedIn();
    }
}
