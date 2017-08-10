package com.assetowl.android.ui.login.observer;

import com.assetowl.android.ui.login.LoginPresenter;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.utils.exception.AssetOwlException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;



import retrofit2.adapter.rxjava.HttpException;

import static junit.framework.Assert.fail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 9/3/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpException.class)
public class LoginObserverTest {
    private LoginObserver loginObserver;

    @Mock
    private LoginPresenter loginPresenter;

    @Mock
    private SessionInfo sessionInfo;


    @Before
    public void setUp() throws Exception {
        loginObserver = new LoginObserver();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowErrorForNullLoginPresenter() {
        loginObserver.setLoginPresenter(null);
    }

    @Test
    public void shouldSetNonNullLoginPresenter() {
        try {
            loginObserver.setLoginPresenter(loginPresenter);
        } catch (RuntimeException rte) {
            fail();
        }
    }

    @Test
    public void shouldMoveToDashboardOnNext() throws Exception {
        loginObserver.setLoginPresenter(loginPresenter);
        when(sessionInfo.getTosAccepted()).thenReturn(true);
        loginObserver.onNext(sessionInfo);
        verify(loginPresenter).moveToDashboard();
        when(sessionInfo.getTosAccepted()).thenReturn(false);
        loginObserver.onNext(sessionInfo);
        verify(loginPresenter).termsAndConditionContent();
    }

    @Test
    public void shouldShowAuthErrorOnError() throws Exception {
        loginObserver.setLoginPresenter(loginPresenter);
        loginObserver.onError(new AssetOwlException("",AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD));
        verify(loginPresenter).setInvalidUsernameOrPasswordError();
    }

    @Test
    public void shouldShowNetworkErrorOnError() throws Exception {
        loginObserver.setLoginPresenter(loginPresenter);
        loginObserver.onError(new AssetOwlException("",AssetOwlException.NETWORK_ISSUE));
        loginObserver.onError(new AssetOwlException("",AssetOwlException.NETWORK_TIME_OUT));
        verify(loginPresenter, times(2)).setNetworkError();
    }

    @Test
    public void shouldShowOtherErrorOnError() throws Exception {
        Exception nullPointerException = new NullPointerException();
        loginObserver.setLoginPresenter(loginPresenter);
        loginObserver.onError(nullPointerException);
        verify(loginPresenter).showError(nullPointerException.getLocalizedMessage());
    }

}