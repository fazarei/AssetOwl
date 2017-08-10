package com.assetowl.android.ui.login.observer;

import com.assetowl.android.ui.login.LoginPresenter;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.utils.exception.AssetOwlException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static junit.framework.Assert.fail;

/**
 * Created by farzanehzarei on 5/4/17.
 */

@RunWith(PowerMockRunner.class)
public class TermAndConditionAcceptObserverTest {

    @Mock
    private LoginPresenter loginPresenter;
    @Mock
    private SessionInfo sessionInfo;

    private TermAndConditionAcceptObserver termAndConditionAcceptObserver;

    @Before
    public void setUp() throws Exception {
        termAndConditionAcceptObserver = new TermAndConditionAcceptObserver();
    }

    @Test
    public void shouldSetNonNullLoginPresenter() {
        try {
            termAndConditionAcceptObserver.setLoginPresenter(loginPresenter);
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    public void moveToDashboardOnNext() throws Exception {
        termAndConditionAcceptObserver.setLoginPresenter(loginPresenter);
        termAndConditionAcceptObserver.onNext(sessionInfo);
        verify(loginPresenter).moveToDashboard();
    }

    @Test
    public void showErrorOnNetwork() throws Exception {
        termAndConditionAcceptObserver.setLoginPresenter(loginPresenter);
        termAndConditionAcceptObserver.onError(new AssetOwlException("",AssetOwlException.NETWORK_ISSUE));
        termAndConditionAcceptObserver.onError(new AssetOwlException("",AssetOwlException.NETWORK_TIME_OUT));
        verify(loginPresenter,times(2)).setNetworkError();
    }

    @Test
    public void showErrorOnOther() throws Exception {
        Exception nullPointerException = new NullPointerException();
        termAndConditionAcceptObserver.setLoginPresenter(loginPresenter);
        termAndConditionAcceptObserver.onError(nullPointerException);
        verify(loginPresenter).showError(nullPointerException.getLocalizedMessage());
    }
}
