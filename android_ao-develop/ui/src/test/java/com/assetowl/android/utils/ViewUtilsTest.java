package com.assetowl.android.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by patrickyin on 25/5/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewUtilsTest {
    @Mock
    RecyclerView recyclerView;
    @Mock
    LinearLayoutManager layoutManager;
    @Mock
    RecyclerView.Adapter adapter;

    @Test
    public void shouldReturnFalseWhenLayoutManagerIsNull() throws Exception {
        when(recyclerView.getAdapter()).thenReturn(adapter);
        assertFalse(ViewUtils.isRecyclerScrollable(recyclerView));
    }
    
    @Test
    public void shouldReturnFalseWhenAdapterIsNull() throws Exception {
        when(recyclerView.getLayoutManager()).thenReturn(layoutManager);
        assertFalse(ViewUtils.isRecyclerScrollable(recyclerView));
    }

    @Test
    public void shouldReturnTrueWhenTheRecyclerViewIsScrollable() throws Exception {
        when(recyclerView.getAdapter()).thenReturn(adapter);
        when(recyclerView.getLayoutManager()).thenReturn(layoutManager);
        when(layoutManager.findLastCompletelyVisibleItemPosition()).thenReturn(5);
        when(adapter.getItemCount()).thenReturn(10);
        assertTrue(ViewUtils.isRecyclerScrollable(recyclerView));
    }

    @Test
    public void shouldReturnFalseWhenTheRecyclerViewIsNotScrollable() throws Exception {
        when(recyclerView.getAdapter()).thenReturn(adapter);
        when(recyclerView.getLayoutManager()).thenReturn(layoutManager);
        when(layoutManager.findLastCompletelyVisibleItemPosition()).thenReturn(5);
        when(adapter.getItemCount()).thenReturn(5);
        assertFalse(ViewUtils.isRecyclerScrollable(recyclerView));
    }
}