package com.assetowl.android.ui.audits.myAudits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assetowl.android.ui.audits.myaudits.MyAuditsAdapter;
import com.assetowl.android.ui.audits.myaudits.MyAuditsHeaderViewHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by farzanehzarei on 11/5/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(LayoutInflater.class)
public class MyAuditsAdapterTest {

    @Mock
    ViewGroup viewGroup;

    @Mock
    LayoutInflater layoutInflater;

    @Mock
    Context context;

    @Mock
    View view;

    @Before
    public void setup() throws Exception {
        PowerMockito.mockStatic(LayoutInflater.class);
    }

    @Test
    public void returnMyAuditHeaderView() throws Exception {
        when(viewGroup.getContext()).thenReturn(context);
        when(context.getApplicationContext()).thenReturn(context);
        when(LayoutInflater.from(context)).thenReturn(layoutInflater);
        when(layoutInflater.inflate(anyInt(), any(ViewGroup.class), anyBoolean())).thenReturn(view);

        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new MyAuditsAdapter();
        assertTrue(adapter.onCreateViewHolder(viewGroup, 0) instanceof MyAuditsHeaderViewHolder);
    }
}
