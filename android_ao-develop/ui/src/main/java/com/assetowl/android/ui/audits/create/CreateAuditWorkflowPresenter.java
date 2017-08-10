package com.assetowl.android.ui.audits.create;

import com.assetowl.android.ui.audits.create.CreateAuditWorkflowView;
import com.assetowl.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by farzanehzarei on 6/6/17.
 */

public class CreateAuditWorkflowPresenter extends BasePresenter<CreateAuditWorkflowView> {

    @Inject
    public CreateAuditWorkflowPresenter(){}

    @Override
    public void start() {
        getView().initialiseView();
    }

    public void initialiseOnCreate() {
        getView().initialiseViewPager();
    }
}
