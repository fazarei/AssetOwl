package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.domain.event.AnalyticsEventBuilder;
import com.assetowl.android.mvp.domain.repository.AnalyticsRepository;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;

import static android.os.Build.VERSION_CODES.M;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by farzanehzarei on 24/3/17.
 */
@RunWith(PowerMockRunner.class)
public class LogoutUseCaseTest {

    private LogoutUseCase logoutUseCase;
    @Mock
    private RxEventBus eventBus;
    @Mock
    private PostExecutionThread uiThread;
    @Mock
    private ThreadExecutor threadExecuter;
    @Mock
    private LoginRepository loginRepository;
    @Mock
    private Observable observable;
    @Mock
    private AnalyticsEventBuilder analyticsEventBuilder;
    @Mock
    private AnalyticsRepository analyticsRepository;

    @Before
    public void setUp() throws Exception {

        Mockito.when(loginRepository.logout()).thenReturn(observable.just(null));
        Mockito.when(loginRepository.logoutFromServer()).thenReturn(observable.just(null));
        logoutUseCase = new UseCaseUnderTest(analyticsEventBuilder, analyticsRepository, loginRepository, threadExecuter, uiThread, eventBus);
    }

    @Test
    public void shouldLogoutToRepositoryWhenBuildingObservable() {
        ((UseCaseUnderTest) logoutUseCase).testBuildUseCaseObserver();

        verify(loginRepository, times(1)).logoutFromServer();
        verify(loginRepository, times(1)).logout();
    }
    
    private class UseCaseUnderTest extends LogoutUseCase {

        UseCaseUnderTest(AnalyticsEventBuilder analyticsEventBuilder, AnalyticsRepository analyticsRepository, LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
            super(analyticsEventBuilder, analyticsRepository, loginRepository, threadExecutor, postExecutionThread, eventBus);
        }

        void testBuildUseCaseObserver() {
            buildUseCaseObservable();
        }
    }
}