package com.assetowl.android.ui.audits.create.templates.observer;

import com.assetowl.android.ui.audits.create.properties.SelectPropertiesPresenter;

import javax.inject.Inject;

/**
 * Created by patrickyin on 7/6/17.
 */

public class PropertiesListObserver {
    private SelectPropertiesPresenter selectPropertiesPresenter;

    @Inject
    public PropertiesListObserver() {
    }

    public void setSelectPropertiesPresenter(SelectPropertiesPresenter selectPropertiesPresenter) {
        this.selectPropertiesPresenter = selectPropertiesPresenter;
    }
}
