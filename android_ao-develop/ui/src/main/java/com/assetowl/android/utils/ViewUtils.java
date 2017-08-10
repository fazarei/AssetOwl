package com.assetowl.android.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by patrickyin on 25/5/17.
 */

public final class ViewUtils {
    public static boolean isRecyclerScrollable(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        return !(layoutManager == null || adapter == null) && layoutManager.findLastCompletelyVisibleItemPosition() < adapter.getItemCount() - 1;
    }
}
