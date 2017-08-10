package com.assetowl.android.ui.dashboard.observer;

import android.support.annotation.NonNull;
import android.util.Log;

import com.assetowl.android.mvp.domain.interactor.DefaultObserver;
import com.assetowl.android.ui.dashboard.DashboardPresenter;
import com.assetowl.domain.featureToggle.model.FeatureToggleInfo;
import com.assetowl.domain.utils.exception.AssetOwlException;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;

/**
 * Created by farzanehzarei on 15/5/17.
 */

public class FeatureToggleObserver extends DefaultObserver<List<FeatureToggleInfo>>{

    private DashboardPresenter dashboardPresenter;

    @Inject
    FeatureToggleObserver() {}

    public void setDashboardPresenter(@NonNull DashboardPresenter dashboardPresenter) {
        this.dashboardPresenter = Preconditions.checkNotNull(dashboardPresenter);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof AssetOwlException) {
            switch (((AssetOwlException) e).getErrorCode()) {
                case AssetOwlException.NETWORK_ISSUE:
                    break;
                case AssetOwlException.NETWORK_TIME_OUT:
                    break;
                default:
            }
        } else {
            this.dashboardPresenter.showError(e.getLocalizedMessage());
        }
    }

    @Override
    public void onNext(List<FeatureToggleInfo> featureToggleInfoList) {
        Log.d("LEE",""+featureToggleInfoList.size());
    }
}
