package com.assetowl.domain.audits.templates.usecase;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.audits.templates.model.TemplateInfo;
import com.assetowl.domain.audits.templates.repository.TemplatesRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by patrickyin on 11/5/17.
 */

public class TemplatesListUseCase extends UseCase<List<? extends TemplateInfo>> {
    TemplatesRepository templatesRepository;

    @Inject
    public TemplatesListUseCase(TemplatesRepository templatesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
        this.templatesRepository = templatesRepository;
    }

    @Override
    protected Observable<List<? extends TemplateInfo>> buildUseCaseObservable() {
        return templatesRepository.templatesList();
    }
}
