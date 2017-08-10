package com.assetowl.android.ui.audits.create.properties;

import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.android.ui.audits.create.templates.observer.PropertiesListObserver;
import com.assetowl.domain.audits.templates.usecase.PropertiesListUseCase;
import com.assetowl.mvp.presenter.BasePresenter;


import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by patrickyin on 7/6/17.
 */

public class SelectPropertiesPresenter extends BasePresenter<SelectPropertiesView> {
    private final PropertiesListUseCase propertiesListUseCase;
    private final PropertiesListObserver propertiesListObserver;

    @Inject
    public SelectPropertiesPresenter(@Named("selectProperties") UseCase propertiesListUseCase,
                                     PropertiesListObserver propertiesListObserver) {
        this.propertiesListUseCase = (PropertiesListUseCase) propertiesListUseCase;
        this.propertiesListObserver = propertiesListObserver;
        this.propertiesListObserver.setSelectPropertiesPresenter(this);
    }

    @Override
    public void start() {
        getView().initialiseView();
    }
}
