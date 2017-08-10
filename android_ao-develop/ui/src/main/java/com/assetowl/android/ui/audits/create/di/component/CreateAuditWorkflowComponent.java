package com.assetowl.android.ui.audits.create.di.component;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.mvp.di.component.ActivityComponent;
import com.assetowl.android.mvp.di.module.ActivityModule;
import com.assetowl.android.mvp.di.scopes.PerActivity;
import com.assetowl.android.ui.audits.create.CreateAuditWorkflowActivity;
import com.assetowl.android.ui.audits.create.CreateAuditWorkflowPresenter;

import dagger.Component;

/**
 * Created by patrickyin on 9/3/17.
 */

@PerActivity
@Component(dependencies = AssetOwlApplicationComponent.class, modules = {ActivityModule.class})
public interface CreateAuditWorkflowComponent extends ActivityComponent {

    void inject(CreateAuditWorkflowActivity createAuditWorkflowActivity);

    CreateAuditWorkflowPresenter getPresenter();
}
