package com.assetowl.android.ui.audits.create.templates.observer;

import com.assetowl.android.ui.audits.create.templates.SelectAuditTemplatesPresenter;
import com.assetowl.domain.audits.templates.model.TemplateInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by patrickyin on 16/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TemplatesListObserverTest {

    private TemplatesListObserver templatesListObserver;

    @Mock
    private SelectAuditTemplatesPresenter selectAuditTemplatesPresenter;

    @Before
    public void setUp() throws Exception {
        templatesListObserver = new TemplatesListObserver();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowErrorForNullLoginPresenter() {
        templatesListObserver.setSelectAuditTemplatesPresenter(null);
    }

    @Test
    public void shouldSetTemplatesListDataOnNext() throws Exception {
        List<? extends TemplateInfo> templatesList = new ArrayList<>();
        templatesListObserver.setSelectAuditTemplatesPresenter(selectAuditTemplatesPresenter);
        templatesListObserver.onNext(templatesList);
        verify(selectAuditTemplatesPresenter).setData(templatesList);
    }

}