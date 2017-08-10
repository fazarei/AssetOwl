package com.assetowl.android.ui.dashboard.observer;

import com.assetowl.android.ui.dashboard.DashboardPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import static junit.framework.Assert.fail;

/**
 * Created by farzanehzarei on 26/5/17.
 */
@RunWith(PowerMockRunner.class)
public class MenuHeaderUserNameObserverTest {

    private MenuHeaderUserNameObserver menuHeaderUserNameObserver;
    @Mock
    private DashboardPresenter dashboardPresenter;

    @Before
    public void setup() throws Exception {
      menuHeaderUserNameObserver = new MenuHeaderUserNameObserver();
    }

    @Test
    public void shouldSetDashboardPresenter() {
        try {
            menuHeaderUserNameObserver.setDashboardPresenter(dashboardPresenter);
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    public void menuHeaderUsernameleOnNext() throws Exception {
        menuHeaderUserNameObserver.setDashboardPresenter(dashboardPresenter);
        menuHeaderUserNameObserver.onNext("support@asetowl.com");
    }
}
