package com.assetowl.mvp.presenter;

import com.assetowl.mvp.ui.DataView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jamespott on 29/5/17.
 */
public class BasePresenterTest {


    private TestSubject testSubject;

    @Mock DataView dataView;

    @Before
    public void beforeEachTest() throws Exception {
        MockitoAnnotations.initMocks(this);
        testSubject = new TestSubject();
    }

    @Test
    public void shouldAttachView() {
        testSubject.attachView(dataView);
        assertThat(dataView, is(testSubject.getView()));
    }


    class TestSubject extends BasePresenter<DataView> {

        @Override
        public void start() {

        }
    }

}