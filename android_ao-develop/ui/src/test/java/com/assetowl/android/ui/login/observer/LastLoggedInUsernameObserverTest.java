package com.assetowl.android.ui.login.observer;

import com.assetowl.android.ui.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

/**
 * Created by patrickyin on 20/4/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LastLoggedInUsernameObserverTest {
    private LastLoggedInUsernameObserver lastLoggedInUsernameObserver;

    @Mock
    private LoginPresenter loginPresenter;

    @Before
    public void setUp() throws Exception {
        lastLoggedInUsernameObserver = new LastLoggedInUsernameObserver();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowErrorForNullLoginPresenter() {
        lastLoggedInUsernameObserver.setLoginPresenter(null);
    }

    @Test
    public void shouldSetNonNullLoginPresenter() {
        try {
            lastLoggedInUsernameObserver.setLoginPresenter(loginPresenter);
        } catch (RuntimeException rte) {
            fail();
        }
    }

    @Test
    public void shouldSetDefaultUsernameOnNext() throws Exception {
        String username = "test@test.test";
        lastLoggedInUsernameObserver.setLoginPresenter(loginPresenter);
        lastLoggedInUsernameObserver.onNext(username);
        verify(loginPresenter).setDefaultUsername(username);
    }

}