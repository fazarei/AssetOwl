package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;

import rx.Observable;

/**
 * Created by patrickyin on 19/4/17.
 */

public class LastLoggedInUsernameUseCase extends UseCase<String> {
    private final LoginRepository loginRepository;
    public LastLoggedInUsernameUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
        this.loginRepository = loginRepository;
    }

    @Override
    protected Observable<String> buildUseCaseObservable() {
        return loginRepository.lastLoggedInUsername();
    }
}
