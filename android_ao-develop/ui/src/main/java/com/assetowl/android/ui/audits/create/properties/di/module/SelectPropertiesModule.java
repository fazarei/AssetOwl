package com.assetowl.android.ui.audits.create.properties.di.module;

import com.assetowl.android.mvp.di.scopes.PerFragment;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.audits.templates.usecase.PropertiesListUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by patrickyin on 7/6/17.
 */

@Module
public class SelectPropertiesModule {
    @Provides
    @PerFragment
    @Named("selectProperties")
    UseCase providePropertiesListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new PropertiesListUseCase(threadExecutor, postExecutionThread, eventBus);
    }
}
