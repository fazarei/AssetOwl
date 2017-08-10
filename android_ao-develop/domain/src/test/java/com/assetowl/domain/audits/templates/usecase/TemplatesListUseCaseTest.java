package com.assetowl.domain.audits.templates.usecase;

import com.assetowl.android.mvp.event.RxEventBus;
import com.assetowl.android.mvp.executor.ThreadExecutor;
import com.assetowl.android.mvp.executor.rx.PostExecutionThread;
import com.assetowl.domain.audits.templates.repository.TemplatesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by patrickyin on 16/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TemplatesListUseCaseTest {

    private TemplatesListUseCase templatesListUseCase;

    @Mock
    TemplatesRepository templatesRepository;

    @Mock
    ThreadExecutor threadExecutor;

    @Mock
    PostExecutionThread postExecutionThread;

    @Mock
    RxEventBus eventBus;

    @Before
    public void setUp() throws Exception {
        templatesListUseCase = new UseCaseUnderTest(templatesRepository, threadExecutor, postExecutionThread, eventBus);
    }

    @Test
    public void shouldPerformTemplatesListMethodWhenBuildingObservable() {
        ((UseCaseUnderTest)templatesListUseCase).testBuildUseCaseObserver();
        verify(templatesRepository).templatesList();
    }


    private class UseCaseUnderTest extends TemplatesListUseCase {
        public UseCaseUnderTest(TemplatesRepository templatesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RxEventBus eventBus) {
            super(templatesRepository, threadExecutor, postExecutionThread, eventBus);
        }
        void testBuildUseCaseObserver() {
            buildUseCaseObservable();
        }
    }
}