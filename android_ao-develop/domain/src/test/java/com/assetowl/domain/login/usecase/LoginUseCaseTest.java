package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.domain.event.AnalyticsEventBuilder;
import com.assetowl.android.mvp.domain.repository.AnalyticsRepository;
import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.analytics.builder.LoginAnalyticsEventBuilder;
import com.assetowl.domain.login.model.UserCredentials;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.session.model.SessionInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;
import rx.functions.Func1;

import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jamespott on 17/2/17.
 */
@RunWith(PowerMockRunner.class)
public class LoginUseCaseTest {

    private LoginUseCase loginUseCase;
    @Mock
    private RxEventBus eventBus;
    @Mock
    private PostExecutionThread uiThread;
    @Mock
    private ThreadExecutor threadExecuter;
    @Mock
    private LoginRepository loginRepository;
    @Mock
    private UserCredentials userCredentials;
    @Mock
    private AnalyticsEventBuilder analyticsEventBuilder;
    @Mock
    private AnalyticsRepository analyticsRepository;
    @Mock
    private Observable<SessionInfo> observable;

    @Before
    public void setUp() throws Exception {
        when(loginRepository.login(userCredentials)).thenReturn(observable);

        loginUseCase = new UseCaseUnderTest(analyticsEventBuilder, analyticsRepository, loginRepository, threadExecuter, uiThread, eventBus);
    }

    @Test
     public void shouldSetNonNullUserCredentials() {
        try {
            loginUseCase.setUserCredentials(userCredentials);
        } catch (RuntimeException rte) {
            fail();
        }
    }

    @Test
    public void shouldLoginToRepositoryWhenBuildingObservable() {
        loginUseCase.setUserCredentials(userCredentials);
        ((UseCaseUnderTest)loginUseCase).testBuildUseCaseObserver();
        verify(loginRepository).login(userCredentials);
    }

    @Test
    public void shouldAddErrorHandlerWhenBuildingObservable() {
        loginUseCase.setUserCredentials(userCredentials);
        ((UseCaseUnderTest)loginUseCase).testBuildUseCaseObserver();

        verify(loginRepository.login(userCredentials)).onErrorResumeNext(isA(Observable.class));
    }



    private class UseCaseUnderTest extends LoginUseCase {

        UseCaseUnderTest(AnalyticsEventBuilder analyticsEventBuilder, AnalyticsRepository analyticsRepository, LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
            super(analyticsEventBuilder, analyticsRepository, loginRepository, threadExecutor, postExecutionThread, eventBus);
        }

        void testBuildUseCaseObserver() {
            buildUseCaseObservable();
        }
    }
}