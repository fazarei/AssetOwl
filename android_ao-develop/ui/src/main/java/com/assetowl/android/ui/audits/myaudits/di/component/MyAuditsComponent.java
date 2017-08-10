package com.assetowl.android.ui.audits.myaudits.di.component;

import com.assetowl.android.di.component.AssetOwlApplicationComponent;
import com.assetowl.android.mvp.di.module.FragmentModule;
import com.assetowl.android.mvp.di.scopes.PerFragment;
import com.assetowl.android.ui.audits.myaudits.MyAuditsFragment;
import com.assetowl.android.ui.audits.myaudits.MyAuditsPresenter;

import dagger.Component;

/**
 * Created by farzanehzarei on 10/5/17.
 */

@PerFragment
@Component(dependencies = AssetOwlApplicationComponent.class, modules = {FragmentModule.class})
public interface MyAuditsComponent {
    void inject(MyAuditsFragment myAuditsFragment);

    MyAuditsPresenter getPresenter();
}
