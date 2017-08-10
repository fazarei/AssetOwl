package com.assetowl.android.ui.audits.myaudits;

import com.assetowl.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by farzanehzarei on 10/5/17.
 */

public class MyAuditsPresenter extends BasePresenter<MyAuditsView> {

    @Inject
    public MyAuditsPresenter() {
    }

    @Override
    public void start() {
        getView().initialiseView();
        getView().initialiseListener();
    }

    public void setNewAuditFilter() {
        getView().updateNewAuditFilterUI();
    }

    public void setInProgressFilter() {
        getView().updateInProgressFilterUI();
    }

    public void setSubmittedFilter() {
        getView().updateSubmittedFilterUI();
    }

    public void loadAuditTemplatesPage() {
        getView().replaceMainContentFragmentWithTemplatesList();
    }
}
