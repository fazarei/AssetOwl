package com.assetowl.android.ui.login.di.module;

import com.assetowl.android.data.login.repository.LoginAnalyticsRepository;
import com.assetowl.android.data.login.repository.LogoutAnalyticsRepository;
import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.analytics.builder.LoginAnalyticsEventBuilder;
import com.assetowl.domain.login.analytics.builder.LogoutAnalyticsEventBuilder;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.login.usecase.InitialNameUseCase;
import com.assetowl.domain.login.usecase.LastLoggedInUsernameUseCase;
import com.assetowl.domain.login.usecase.LoginUseCase;
import com.assetowl.domain.login.usecase.LogoutUseCase;
import com.assetowl.domain.login.usecase.TermAndConditionAcceptUseCase;
import com.assetowl.domain.login.usecase.TermAndConditionUseCase;
import com.assetowl.domain.login.usecase.VerifyLoggedInUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jamespott on 7/2/17.
 */
@Module
public class LoginModule {

    @Provides
    @PerActivity
    @Named("login")
    UseCase provideLoginUseCase(LoginAnalyticsEventBuilder loginAnalyticsEventBuilder, LoginAnalyticsRepository loginAnalyticsRepository, LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new LoginUseCase(loginAnalyticsEventBuilder, loginAnalyticsRepository, loginRepository, threadExecutor, postExecutionThread, eventBus);
    }

    @Provides
    @PerActivity
    @Named("verifyLogin")
    UseCase provideVerifyLoggedInUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new VerifyLoggedInUseCase(loginRepository, threadExecutor, postExecutionThread, eventBus);
    }

    @Provides
    @PerActivity
    @Named("logout")
    UseCase provideLogoutUseCase(LogoutAnalyticsEventBuilder logoutAnalyticsEventBuilder, LogoutAnalyticsRepository logoutAnalyticsRepository, LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new LogoutUseCase(logoutAnalyticsEventBuilder, logoutAnalyticsRepository, loginRepository, threadExecutor, postExecutionThread, eventBus);
    }

    @Provides
    @PerActivity
    @Named("termAndCondition")
    UseCase provideTermAndConditionUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new TermAndConditionUseCase(loginRepository, threadExecutor, postExecutionThread, eventBus);
    }

    @Provides
    @PerActivity
    @Named("termAndConditionAccept")
    UseCase provideTermAndConditionAcceptUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new TermAndConditionAcceptUseCase(loginRepository, threadExecutor, postExecutionThread, eventBus);
    }

    @Provides
    @PerActivity
    @Named("lastLoggedInUsername")
    UseCase provideLastLoggedInUsernameUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new LastLoggedInUsernameUseCase(loginRepository, threadExecutor, postExecutionThread, eventBus);
    }

    @Provides
    @PerActivity
    @Named("initialName")
    UseCase provideInitialNameUseCase(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new InitialNameUseCase(loginRepository, threadExecutor, postExecutionThread, eventBus);
    }

}
