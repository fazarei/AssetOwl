package com.assetowl.android.ui.audits.create.templates.di.module;

import com.assetowl.android.mvp.di.scopes.PerFragment;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.audits.templates.repository.TemplatesRepository;
import com.assetowl.domain.audits.templates.usecase.TemplatesListUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by patrickyin on 11/5/17.
 */

@Module
public class SelectAuditTemplatesModule {
    @Provides
    @PerFragment
    @Named("selectAuditTemplates")
    UseCase provideTemplatesListUseCase(TemplatesRepository templatesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        return new TemplatesListUseCase(templatesRepository, threadExecutor, postExecutionThread, eventBus);
    }
}
