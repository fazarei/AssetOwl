package com.assetowl.android.ui.audits.create.properties;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assetowl.android.R;
import com.assetowl.domain.audits.templates.model.PropertyInfo;

import java.util.List;

/**
 * Created by patrickyin on 7/6/17.
 */

class PropertiesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<? extends PropertyInfo> propertiesList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext())
                .inflate(R.layout.recyclerview_properties_list_row, parent, false);
        return new PropertiesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(this.propertiesList == null) {
            return 0;
        } else {
            return this.propertiesList.size();
        }
    }
}
