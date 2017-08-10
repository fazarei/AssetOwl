package com.assetowl.android.ui.audits.create.templates.observer;

import android.support.annotation.NonNull;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.audits.create.templates.SelectAuditTemplatesPresenter;
import com.assetowl.mvp.utils.Preconditions;
import com.assetowl.domain.audits.templates.model.TemplateInfo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by patrickyin on 11/5/17.
 */

public class TemplatesListObserver extends DefaultObserver<List<? extends TemplateInfo>> {
    private SelectAuditTemplatesPresenter selectAuditTemplatesPresenter;

    @Inject
    public TemplatesListObserver() {
    }

    public void setSelectAuditTemplatesPresenter(@NonNull SelectAuditTemplatesPresenter loginPresenter) {
        this.selectAuditTemplatesPresenter = Preconditions.checkNotNull(loginPresenter);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(List<? extends TemplateInfo> templatesList) {
        this.selectAuditTemplatesPresenter.setData(templatesList);
    }
}
