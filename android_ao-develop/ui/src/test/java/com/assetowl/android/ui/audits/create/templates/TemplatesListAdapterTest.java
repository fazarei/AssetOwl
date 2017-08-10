package com.assetowl.android.ui.audits.create.templates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assetowl.domain.audits.templates.model.Stats;
import com.assetowl.domain.audits.templates.model.TemplateInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by patrickyin on 9/5/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({LayoutInflater.class, ButterKnife.class})
public class TemplatesListAdapterTest {
    @Mock
    ViewGroup viewGroup;
    @Mock
    Context context;
    @Mock
    LayoutInflater layoutInflater;
    @Mock
    View view;
    @Mock
    TemplateInfo templateInfo;
    @Mock
    TemplatesListViewHolder templatesListViewHolder;
    @Mock
    TextView textViewAuditTemplates, textViewQuestions, textViewId;
    @Mock
    Stats stats;

    private TemplatesListAdapter adapter;
    private List<TemplateInfo> templatesList;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(LayoutInflater.class);
        PowerMockito.mockStatic(ButterKnife.class);

        when(viewGroup.getContext()).thenReturn(context);
        when(context.getApplicationContext()).thenReturn(context);
        when(LayoutInflater.from(context)).thenReturn(layoutInflater);
        when(layoutInflater.inflate(anyInt(), any(ViewGroup.class), anyBoolean())).thenReturn(view);
        adapter = new TemplatesListAdapter();
    }

    @Test
    public void shouldReturnTemplatesListViewHolderWhenTypeIsNotHeader() throws Exception {
        assertTrue(adapter.onCreateViewHolder(viewGroup, 1) instanceof TemplatesListViewHolder);
    }

    @Test
    public void shouldReturnItemCount2WhenTemplatesListSizeIs1() throws Exception {
        templatesList = new ArrayList<>();
        templatesList.add(templateInfo);
        adapter.setTemplatesList(templatesList);
    }

    @Test
    public void shouldReturnCorrectItemType() throws Exception {
        assertEquals(0, adapter.getItemViewType(0));
    }

    @Test
    public void shouldBindListItemViewHolderWhenAdapterInvokeOnBindViewHolder() throws Exception {
        templatesListViewHolder.textViewAuditTemplates = textViewAuditTemplates;
        templatesListViewHolder.textViewQuestions = textViewQuestions;
        templatesListViewHolder.textViewId = textViewId;
        when(templateInfo.getIdString()).thenReturn("");
        when(templateInfo.getName()).thenReturn("");
        when(templateInfo.getStats()).thenReturn(stats);
        when(stats.getTotalQuestionsString()).thenReturn("");

        templatesList = new ArrayList<>();
        templatesList.add(templateInfo);
        adapter.setTemplatesList(templatesList);
    }
}