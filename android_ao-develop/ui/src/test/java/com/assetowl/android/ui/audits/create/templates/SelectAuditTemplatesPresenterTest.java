package com.assetowl.android.ui.audits.create.templates;

import com.assetowl.android.ui.audits.create.templates.observer.TemplatesListObserver;
import com.assetowl.domain.audits.templates.model.TemplateInfo;
import com.assetowl.domain.audits.templates.usecase.TemplatesListUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by patrickyin on 9/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectAuditTemplatesPresenterTest {

    @Mock
    TemplatesListUseCase templatesListUseCase;
    @Mock
    TemplatesListObserver templatesListObserver;

    @Mock
    SelectAuditTemplatesView selectAuditTemplatesView;

    SelectAuditTemplatesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SelectAuditTemplatesPresenter(templatesListUseCase, templatesListObserver);
        presenter.attachView(selectAuditTemplatesView);
        presenter.start();
    }

    @Test
    public void shouldInitialiseView() throws Exception {
        verify((SelectAuditTemplatesView)presenter.getView()).initialiseView();
    }

    @Test
    public void shouldSetDataToView() throws Exception {
        List<? extends TemplateInfo> templatesListData = new ArrayList<>();
        presenter.setData(templatesListData);
        verify(presenter.getView()).setData(templatesListData);
    }
}