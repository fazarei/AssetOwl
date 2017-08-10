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

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by farzanehzarei on 1/6/17.
 */
@RunWith(PowerMockRunner.class)
public class InitialNameUseCaseTest {
    private InitialNameUseCase initialiseNameUseCase;
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
        when(loginRepository.initialName()).thenReturn(observable);

        initialiseNameUseCase = new UseCaseUnderTest(loginRepository, threadExecuter, uiThread, eventBus);
    }


    @Test
    public void shouldPerformInitialiseNameWhenBuildingObservable() {
        ((UseCaseUnderTest)initialiseNameUseCase).testBuildUseCaseObserver();

        verify(loginRepository).initialName();
    }


    private class UseCaseUnderTest extends InitialNameUseCase {

        UseCaseUnderTest(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
            super(loginRepository, threadExecutor, postExecutionThread, eventBus);
        }

        void testBuildUseCaseObserver() {
            buildUseCaseObservable();
        }
    }
}
