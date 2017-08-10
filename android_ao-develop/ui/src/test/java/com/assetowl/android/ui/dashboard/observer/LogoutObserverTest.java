package com.assetowl.android.ui.dashboard.observer;

import com.assetowl.android.ui.dashboard.DashboardPresenter;
import com.assetowl.domain.login.repository.LoggedInStatus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.verify;

/**
 * Created by farzanehzarei on 22/3/17.
 */
@RunWith(PowerMockRunner.class)
public class LogoutObserverTest {

    private LogoutObserver logoutObserver;

    @Mock
    private DashboardPresenter dashboardPresenter;

    @Before
    public void setup() throws Exception {
        logoutObserver = new LogoutObserver();
    }

    @Test
    public void logouFailedTest() throws Exception {
        logoutObserver.setDashboardPresenter(dashboardPresenter);
        logoutObserver.onNext(LoggedInStatus.NO);
        verify(dashboardPresenter).logoutSuccess();
    }
}
