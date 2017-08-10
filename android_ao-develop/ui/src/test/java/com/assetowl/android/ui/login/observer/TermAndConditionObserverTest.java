package com.assetowl.android.ui.login.observer;

import com.assetowl.android.ui.login.LoginPresenter;
import com.assetowl.domain.session.model.TermsAndConditionInfo;
import com.assetowl.domain.utils.exception.AssetOwlException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by farzanehzarei on 5/4/17.
 */

@RunWith(PowerMockRunner.class)
public class TermAndConditionObserverTest {

    @Mock
    private LoginPresenter loginPresenter;
    @Mock
    private TermsAndConditionInfo termsAndConditionInfo;

    private TermAndConditionObserver termAndConditionObserver;

    @Before
    public void setUp() throws Exception {
        termAndConditionObserver = new TermAndConditionObserver();
    }

    @Test
    public void shouldSetNonNullLoginPresenter() {
        try {
            termAndConditionObserver.setLoginPresenter(loginPresenter);
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    public void showTAndCContentOnNext() throws Exception {
        termAndConditionObserver.setLoginPresenter(loginPresenter);
        termAndConditionObserver.onNext(termsAndConditionInfo);
        verify(loginPresenter).showTAndCContent(null,null);
    }

    @Test
    public void ShowNetworkError() throws Exception {
        termAndConditionObserver.setLoginPresenter(loginPresenter);
        termAndConditionObserver.onError(new AssetOwlException("",AssetOwlException.NETWORK_ISSUE));
        termAndConditionObserver.onError(new AssetOwlException("",AssetOwlException.NETWORK_TIME_OUT));
        verify(loginPresenter,times(2)).setNetworkError();
    }

    @Test
    public void showOtherError() throws Exception {
        Exception nullPointerException = new NullPointerException();
        termAndConditionObserver.setLoginPresenter(loginPresenter);
        termAndConditionObserver.onError(nullPointerException);
        verify(loginPresenter).showError(nullPointerException.getLocalizedMessage());
    }
}
