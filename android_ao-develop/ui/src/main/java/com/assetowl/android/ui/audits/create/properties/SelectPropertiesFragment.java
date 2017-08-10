package com.assetowl.android.ui.audits.create.properties;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.R;
import com.assetowl.android.mvp.ui.PresenterFragment;

import butterknife.Bind;

/**
 * Created by patrickyin on 7/6/17.
 */

public class SelectPropertiesFragment extends PresenterFragment<SelectPropertiesPresenter> implements SelectPropertiesView {

    @Bind(R.id.recycler_view_templates_properties_audits_list)
    RecyclerView recyclerView;
    private PropertiesListAdapter adapter;

    @Override
    public void initialiseView() {
        adapter = new PropertiesListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initialiseInjector() {
        final AssetOwlApplication application = (AssetOwlApplication) getActivity().getApplicationContext();

        application.getComponentFactory()
                .createSelectPropertiesComponent(getFragmentModule())
                .inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_templates_properties_audits_list;
    }

}
