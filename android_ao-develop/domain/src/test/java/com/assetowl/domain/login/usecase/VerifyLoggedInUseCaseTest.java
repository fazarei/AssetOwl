package com.assetowl.domain.login.usecase;

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

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by farzanehzarei on 24/3/17.
 */
@RunWith(PowerMockRunner.class)
public class VerifyLoggedInUseCaseTest {
    private VerifyLoggedInUseCase verifyLoggedInUseCase;
    @Mock
    private RxEventBus eventBus;
    @Mock
    private PostExecutionThread uiThread;
    @Mock
    private ThreadExecutor threadExecuter;
    @Mock
    private LoginRepository loginRepository;
    @Mock
    private Observable<String> observable;

    @Before
    public void setUp() throws Exception {
        Mockito.when(loginRepository.hasUserLoggedIn()).thenReturn(observable.just("YES"));
        verifyLoggedInUseCase = new UseCaseUnderTest(loginRepository, threadExecuter, uiThread, eventBus);
    }

    @Test
    public void shouldVerifyToRepositoryWhenBuildingObservable() {
        ((UseCaseUnderTest) verifyLoggedInUseCase).testBuildUseCaseObserver();

        verify(loginRepository, times(1)).hasUserLoggedIn();
    }

    private class UseCaseUnderTest extends VerifyLoggedInUseCase {

        UseCaseUnderTest(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
            super(loginRepository, threadExecutor, postExecutionThread, eventBus);
        }

        void testBuildUseCaseObserver() {
            buildUseCaseObservable();
        }
    }
}
