package com.assetowl.android.ui.dashboard.observer;

import com.assetowl.android.ui.dashboard.DashboardPresenter;
import com.assetowl.domain.featureToggle.model.FeatureToggleInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by farzanehzarei on 16/5/17.
 */
@RunWith(PowerMockRunner.class)
public class FeatureToggleObserverTest {
    @Mock
    private DashboardPresenter dashboardPresenter;
    @Mock
    private FeatureToggleInfo featureToggleInfo;

    private FeatureToggleObserver featureToggleObserver;
    private List<FeatureToggleInfo> featureToggleInfoList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        featureToggleObserver = new FeatureToggleObserver();
    }

    @Test
    public void shouldSetNonNullLoginPresenter() {
        try {
            featureToggleObserver.setDashboardPresenter(dashboardPresenter);
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    public void featureToggleOnNext() throws Exception {
        featureToggleObserver.setDashboardPresenter(dashboardPresenter);
        featureToggleObserver.onNext(featureToggleInfoList);
    }

    @Test
    public void showOtherError() throws Exception {
        Exception nullPointerException = new NullPointerException();
        featureToggleObserver.setDashboardPresenter(dashboardPresenter);
        featureToggleObserver.onError(nullPointerException);
        verify(dashboardPresenter).showError(nullPointerException.getLocalizedMessage());
    }
}
