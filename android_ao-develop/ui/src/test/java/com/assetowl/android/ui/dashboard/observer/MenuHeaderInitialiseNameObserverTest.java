package com.assetowl.android.ui.dashboard.observer;

import com.assetowl.android.ui.dashboard.DashboardPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import static junit.framework.Assert.fail;

/**
 * Created by farzanehzarei on 1/6/17.
 */
@RunWith(PowerMockRunner.class)
public class MenuHeaderInitialiseNameObserverTest {
    private MenuHeaderInitialNameObserver menuHeaderInitialNameObserver;
    @Mock
    private DashboardPresenter dashboardPresenter;

    @Before
    public void setup() throws Exception {
        menuHeaderInitialNameObserver = new MenuHeaderInitialNameObserver();
    }

    @Test
    public void shouldSetDashboardPresenter() {
        try {
            menuHeaderInitialNameObserver.setDashboardPresenter(dashboardPresenter);
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    public void menuHeaderInitialiseOnNext() throws Exception {
        menuHeaderInitialNameObserver.setDashboardPresenter(dashboardPresenter);
        menuHeaderInitialNameObserver.onNext("AS");
    }
}
