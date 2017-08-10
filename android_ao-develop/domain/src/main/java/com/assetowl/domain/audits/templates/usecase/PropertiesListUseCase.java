package com.assetowl.domain.audits.templates.usecase;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.audits.templates.model.PropertyInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by patrickyin on 7/6/17.
 */

public class PropertiesListUseCase extends UseCase<List<? extends PropertyInfo>> {
    public PropertiesListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
        super(threadExecutor, postExecutionThread, eventBus);
    }

    @Override
    protected Observable<List<? extends PropertyInfo>> buildUseCaseObservable() {
        return null;
    }
}
