package com.assetowl.domain.login.usecase;

import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.login.repository.LoginRepository;
import com.assetowl.domain.session.model.SessionInfo;
import com.assetowl.domain.session.model.TermsAndConditionInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by farzanehzarei on 5/4/17.
 */
@RunWith(PowerMockRunner.class)
public class TermAndConditionAcceptUseCaseTest {

    private TermAndConditionAcceptUseCase termAndConditionAcceptUseCase;
    @Mock
    private RxEventBus eventBus;
    @Mock
    private PostExecutionThread uiThread;
    @Mock
    private ThreadExecutor threadExecuter;
    @Mock
    private LoginRepository loginRepository;
    @Mock
    private Observable<SessionInfo> observable;

    private String ACCESS_TOKEN="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE0OTEzNzE1NjAsImlhdCI6MTQ5MTM3MTI2MCwic3ViIjoiZmFyemFuZWguemFyZWkrdGVzdDNAYXNzZXRvd2wuY29tIiwicHJpbmNpcGFsIjoiZmFyemFuZWguemFyZWkrdGVzdDNAYXNzZXRvd2wuY" +
            "29tIiwiY2xpZW50SWQiOjI4LCJwZXJtaXNzaW9ucyI6WyJST0xFX0xJU1QiLCJTVE9SRV9MSVNUIiwiVVNFUl9JTlZJVEUiLCJVU0VSX0xJU1QiXSwidG9zIjpmYWxzZSwiaWQiOjcwMX0.qhsKAlufJgghAeJ2o3KlCoRUwW7lplol3K3X6qB7mSmX5dfXR4RSvyiMX036nAW6J5vaRcncrZbcAoXgJ62Fkg";

    private int VERSION=2;
    @Before
    public void setUp() throws Exception {
        when(loginRepository.termAndConditionAccept()).thenReturn(observable);

        termAndConditionAcceptUseCase = new UseCaseUnderTest(loginRepository, threadExecuter, uiThread, eventBus);
    }

    @Test
    public void shouldLoginToRepositoryWhenBuildingObservable() {

        ((UseCaseUnderTest)termAndConditionAcceptUseCase).testBuildUseCaseObserver();

        verify(loginRepository, times(1)).termAndConditionAccept();
    }

    @Test
    public void shouldAddErrorHandlerWhenBuildingObservable() {

        ((UseCaseUnderTest)termAndConditionAcceptUseCase).testBuildUseCaseObserver();

        verify(loginRepository.termAndConditionAccept()).onErrorResumeNext(isA(Observable.class));
    }



    private class UseCaseUnderTest extends TermAndConditionAcceptUseCase {

        UseCaseUnderTest(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
            super(loginRepository, threadExecutor, postExecutionThread, eventBus);
        }

        void testBuildUseCaseObserver() {
            buildUseCaseObservable();
        }
    }
}
