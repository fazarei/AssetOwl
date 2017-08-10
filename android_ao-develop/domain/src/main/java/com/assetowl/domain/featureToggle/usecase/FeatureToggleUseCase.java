package com.assetowl.domain.featureToggle.usecase;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.featureToggle.model.FeatureToggleInfo;
import com.assetowl.domain.featureToggle.repository.FeatureToggleRepository;
import com.assetowl.domain.utils.exception.filter.NetworkExceptionFilter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by farzanehzarei on 12/5/17.
 */

public class FeatureToggleUseCase extends UseCase {

    private final FeatureToggleRepository featureToggleRepository;

    @Inject
    public FeatureToggleUseCase(FeatureToggleRepository featureToggleRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor,postExecutionThread,eventBus);
        this.featureToggleRepository = featureToggleRepository;
    }

    @Override
    protected Observable<List<? extends FeatureToggleInfo>> buildUseCaseObservable() {
        return featureToggleRepository.featureToggle().onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<? extends List<FeatureToggleInfo>>>());
    }
}
