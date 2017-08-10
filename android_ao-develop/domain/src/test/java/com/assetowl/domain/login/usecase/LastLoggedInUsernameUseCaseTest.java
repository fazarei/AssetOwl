package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 20/4/17.
 */
@RunWith(PowerMockRunner.class)
public class LastLoggedInUsernameUseCaseTest {
    private LastLoggedInUsernameUseCase lastLoggedInUsernameUseCase;
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
        when(loginRepository.lastLoggedInUsername()).thenReturn(observable);

        lastLoggedInUsernameUseCase = new UseCaseUnderTest(loginRepository, threadExecuter, uiThread, eventBus);
    }


    @Test
    public void shouldPerformLastLoggedInUsernameWhenBuildingObservable() {
        ((UseCaseUnderTest)lastLoggedInUsernameUseCase).testBuildUseCaseObserver();

        verify(loginRepository).lastLoggedInUsername();
    }


    private class UseCaseUnderTest extends LastLoggedInUsernameUseCase {

        UseCaseUnderTest(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
            super(loginRepository, threadExecutor, postExecutionThread, eventBus);
        }

        void testBuildUseCaseObserver() {
            buildUseCaseObservable();
        }
    }
}