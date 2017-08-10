package com.assetowl.android.ui.launch.observer;

import com.assetowl.android.ui.launch.LaunchPresenter;
import com.assetowl.domain.login.repository.LoggedInStatus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by farzanehzarei on 22/3/17.
 */

@RunWith(PowerMockRunner.class)
public class LoginAuthoriseObserverTest {
    private LoginAuthoriseObserver loginAuthoriseObserver;

    @Mock
    private LaunchPresenter launchPresenter;

    @Before
    public void setUp() throws Exception {
        loginAuthoriseObserver = new LoginAuthoriseObserver();
    }

    @Test
    public void verifiedLoginTest() throws Exception {
        loginAuthoriseObserver.setLaunchPresenter(launchPresenter);
        loginAuthoriseObserver.onNext(LoggedInStatus.YES);
        verify(launchPresenter).verifyLogin(LoggedInStatus.YES);
    }

    @Test
    public void notVerifiedLoginTest() throws Exception {
        loginAuthoriseObserver.setLaunchPresenter(launchPresenter);
        loginAuthoriseObserver.onNext(LoggedInStatus.NO);
        verify(launchPresenter).verifyLogin(LoggedInStatus.NO);
    }
}
