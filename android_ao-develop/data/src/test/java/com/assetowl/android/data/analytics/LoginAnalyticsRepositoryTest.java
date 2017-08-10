package com.assetowl.android.data.analytics;

import com.assetowl.android.data.login.repository.LoginAnalyticsRepository;
import com.assetowl.android.mvp.domain.event.AnalyticsEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import io.intercom.android.sdk.Intercom;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 11/4/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LoginAnalyticsRepository.class, Intercom.class})
public class LoginAnalyticsRepositoryTest {

    private LoginAnalyticsRepository loginAnalyticsRepository;
    @Mock
    AnalyticsEvent analyticsEvent;
    @Mock
    Intercom intercom;

    @Before
    public void setUp() throws Exception {
        mockStatic(Intercom.class);
        loginAnalyticsRepository = PowerMockito.spy(new LoginAnalyticsRepository());
        when(Intercom.client()).thenReturn(intercom);
    }

    @Test
    public void shouldInvokeLogToIntercomEvent() throws Exception {
        loginAnalyticsRepository.push(analyticsEvent);
        verifyPrivate(loginAnalyticsRepository).invoke("logToIntercomEvent", any(AnalyticsEvent.class));
    }
}