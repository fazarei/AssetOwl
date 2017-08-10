package com.assetowl.android.ui.audits.myAudits;

import com.assetowl.android.ui.audits.myaudits.MyAuditsPresenter;
import com.assetowl.android.ui.audits.myaudits.MyAuditsView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by farzanehzarei on 11/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyAuditsPresenterTest {

    @Mock
    MyAuditsView myAuditsView;

    @Test
    public void shouldInitialiseView() throws Exception {
        MyAuditsPresenter presenter = new MyAuditsPresenter();
        presenter.attachView(myAuditsView);
        presenter.start();
        verify(presenter.getView()).initialiseView();
    }

    @Test
    public void shouldInitialiseListener() throws Exception {
        MyAuditsPresenter presenter = new MyAuditsPresenter();
        presenter.attachView(myAuditsView);
        presenter.start();
        verify(presenter.getView()).initialiseListener();
    }

    @Test
    public void shouldUpdateUIWhenSetNewAuditFilter() throws Exception {
        MyAuditsPresenter presenter = new MyAuditsPresenter();
        presenter.attachView(myAuditsView);
        presenter.setNewAuditFilter();
        verify(presenter.getView()).updateNewAuditFilterUI();
    }

    @Test
    public void shouldUpdateUIWhenSetInProgressFilter() throws Exception {
        MyAuditsPresenter presenter = new MyAuditsPresenter();
        presenter.attachView(myAuditsView);
        presenter.setInProgressFilter();
        verify(presenter.getView()).updateInProgressFilterUI();
    }

    @Test
    public void shouldUpdateUIWhenSetSubmittedFilter() throws Exception {
        MyAuditsPresenter presenter = new MyAuditsPresenter();
        presenter.attachView(myAuditsView);
        presenter.setSubmittedFilter();
        verify(presenter.getView()).updateSubmittedFilterUI();
    }
}
