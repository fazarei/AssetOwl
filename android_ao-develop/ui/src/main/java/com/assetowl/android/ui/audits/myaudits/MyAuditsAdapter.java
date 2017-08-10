package com.assetowl.android.ui.audits.myaudits;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assetowl.android.R;

/**
 * Created by farzanehzarei on 10/5/17.
 */

public class MyAuditsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.recycleview_my_audits_header,parent,false);
        return new MyAuditsHeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyAuditsHeaderViewHolder viewHolder = (MyAuditsHeaderViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
