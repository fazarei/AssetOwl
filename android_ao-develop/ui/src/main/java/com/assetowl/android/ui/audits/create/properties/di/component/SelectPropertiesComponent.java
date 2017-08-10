package com.assetowl.android.ui.audits.create.properties.di.component;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.mvp.di.module.FragmentModule;
import com.assetowl.android.mvp.di.scopes.PerFragment;
import com.assetowl.android.ui.audits.create.properties.SelectPropertiesFragment;
import com.assetowl.android.ui.audits.create.properties.SelectPropertiesPresenter;
import com.assetowl.android.ui.audits.create.properties.di.module.SelectPropertiesModule;

import dagger.Component;

/**
 * Created by patrickyin on 8/5/17.
 */

@PerFragment
@Component(dependencies = AssetOwlApplicationComponent.class, modules = {FragmentModule.class, SelectPropertiesModule.class})
public interface SelectPropertiesComponent {
    void inject(SelectPropertiesFragment selectPropertiesFragment);
    SelectPropertiesPresenter getPresenter();
}