package com.assetowl.android.ui.audits.create.templates;

import com.assetowl.android.mvp.di.scopes.PerFragment;
import com.assetowl.android.mvp.domain.interactor.UseCase;
import com.assetowl.mvp.presenter.BasePresenter;
import com.assetowl.android.ui.audits.create.templates.observer.TemplatesListObserver;
import com.assetowl.domain.audits.templates.model.TemplateInfo;
import com.assetowl.domain.audits.templates.usecase.TemplatesListUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by patrickyin on 5/5/17.
 */
@PerFragment
public class SelectAuditTemplatesPresenter extends BasePresenter<SelectAuditTemplatesView> {

    private final TemplatesListUseCase templatesListUseCase;
    private final TemplatesListObserver templatesListObserver;

    @Inject
    public SelectAuditTemplatesPresenter(@Named("selectAuditTemplates") UseCase templatesListUseCase,
                                         TemplatesListObserver templatesListObserver) {
        this.templatesListUseCase = (TemplatesListUseCase) templatesListUseCase;
        this.templatesListObserver = templatesListObserver;
        this.templatesListObserver.setSelectAuditTemplatesPresenter(this);
    }

    @Override
    public void start() {
        getView().initialiseView();
        this.templatesListUseCase.execute(this.templatesListObserver);
    }

    public void setData(List<? extends TemplateInfo> templatesListData) {
        getView().setData(templatesListData);
    }
}
