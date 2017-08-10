package com.assetowl.android.ui.audits.create.templates;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.R;
import com.assetowl.android.mvp.ui.PresenterFragment;
import com.assetowl.android.ui.audits.create.CreateAuditWorkflowActivity;
import com.assetowl.domain.audits.templates.model.TemplateInfo;

import java.util.List;

import butterknife.Bind;


/**
 * Created by patrickyin on 5/5/17.
 */

public class SelectAuditTemplatesFragment extends PresenterFragment<SelectAuditTemplatesPresenter> implements SelectAuditTemplatesView {


    @Bind(R.id.recycler_view_templates_properties_audits_list)
    RecyclerView recyclerView;
    private TemplatesListAdapter adapter;

    FrameLayout frameLayout;



    @Override
    protected void initialiseInjector() {
        final AssetOwlApplication application = (AssetOwlApplication) getActivity().getApplicationContext();

        frameLayout = (FrameLayout) getActivity().findViewById(R.id.main_content);

        application.getComponentFactory()
                .createSelectAuditTemplatesComponent(getFragmentModule())
                .inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_templates_properties_audits_list;
    }

    @Override
    public void initialiseView() {
        adapter = new TemplatesListAdapter();
        adapter.setAfterClickListener(new TemplatesListAdapter.AfterClickListener() {
            @Override
            public void  afterClick() {

                if(adapter.getCheckedItemsCount()>0) {
                    ((CreateAuditWorkflowActivity)getActivity()).enableNextButton();
                } else {
                    ((CreateAuditWorkflowActivity)getActivity()).deActiveNextButton();
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setData(List<? extends TemplateInfo> templatesListData) {
        adapter.setTemplatesList(templatesListData);
        adapter.notifyDataSetChanged();
    }
}
