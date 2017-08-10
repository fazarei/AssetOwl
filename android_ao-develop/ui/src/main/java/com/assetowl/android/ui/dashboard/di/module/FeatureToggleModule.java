package com.assetowl.android.ui.dashboard.di.module;

import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.featureToggle.repository.FeatureToggleRepository;
import com.assetowl.domain.featureToggle.usecase.FeatureToggleUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by farzanehzarei on 17/5/17.
 */
@Module
public class FeatureToggleModule {
    @Provides
    @PerActivity
    @Named("featureList")
    UseCase provideFeatureToggleUseCase(FeatureToggleRepository featureToggleRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
               return new FeatureToggleUseCase(featureToggleRepository, threadExecutor, postExecutionThread, eventBus);
            }
}

