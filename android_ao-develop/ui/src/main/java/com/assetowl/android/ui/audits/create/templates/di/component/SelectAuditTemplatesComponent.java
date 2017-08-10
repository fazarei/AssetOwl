package com.assetowl.android.ui.audits.create.templates.di.component;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.mvp.di.module.FragmentModule;
import com.assetowl.android.mvp.di.scopes.PerFragment;
import com.assetowl.android.ui.audits.create.templates.SelectAuditTemplatesFragment;
import com.assetowl.android.ui.audits.create.templates.SelectAuditTemplatesPresenter;
import com.assetowl.android.ui.audits.create.templates.di.module.SelectAuditTemplatesModule;

import dagger.Component;

/**
 * Created by patrickyin on 8/5/17.
 */

@PerFragment
@Component(dependencies = AssetOwlApplicationComponent.class, modules = {FragmentModule.class, SelectAuditTemplatesModule.class})
public interface SelectAuditTemplatesComponent {
    void inject(SelectAuditTemplatesFragment selectAuditTemplatesFragment);
    SelectAuditTemplatesPresenter getPresenter();
}