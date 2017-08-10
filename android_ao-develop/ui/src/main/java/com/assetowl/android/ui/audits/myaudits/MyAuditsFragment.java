package com.assetowl.android.ui.audits.myaudits;

import android.support.v4.app.Fragment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.assetowl.android.AssetOwlApplication;
import com.assetowl.android.R;
import com.assetowl.android.mvp.ui.PresenterFragment;
import com.assetowl.android.ui.audits.create.templates.SelectAuditTemplatesFragment;
import com.assetowl.android.ui.component.tabbar.AuditStatusFilterTabBar;
import com.assetowl.android.ui.dashboard.DashboardActivity;
import com.assetowl.android.utils.ViewUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by farzanehzarei on 10/5/17.
 */

public class MyAuditsFragment extends PresenterFragment<MyAuditsPresenter> implements MyAuditsView {

    @Bind(R.id.recycle_view_my_audits_list)
    RecyclerView recyclerView;

    @Bind(R.id.my_audits_tabs)
    AuditStatusFilterTabBar auditStatusFilterTabBar;

    @Bind(R.id.my_audits_welcome_card)
    RelativeLayout welcomeCard;

    @Bind(R.id.my_audits_list_header_date)
    TextView listHeaderDate;

    private RecyclerView.Adapter adapter;
    Fragment templatesListFragment = new SelectAuditTemplatesFragment();

    @Override
    protected void initialiseInjector() {
        final AssetOwlApplication application = (AssetOwlApplication) getActivity().getApplicationContext();
        application.getComponentFactory()
                .createMyAuditsComponent(getFragmentModule())
                .inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_audits;
    }

    @Override
    public void initialiseView() {
        adapter = new MyAuditsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        AppBarLayout.LayoutParams appBarLayout = (AppBarLayout.LayoutParams) auditStatusFilterTabBar.getLayoutParams();
        if(ViewUtils.isRecyclerScrollable(recyclerView)) {
            recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_IF_CONTENT_SCROLLS);
            appBarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        } else {
            recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            appBarLayout.setScrollFlags(0);
        }
        if(adapter.getItemCount() == 0) {
            welcomeCard.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initialiseListener() {
        auditStatusFilterTabBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                assert tab.getTag() != null;
                switch ((AuditStatusFilterTabBar.AuditStatusFilterOptions)tab.getTag()) {
                    case NEW_AUDITS:
                        presenter.setNewAuditFilter();
                        break;
                    case IN_PROGRESS:
                        presenter.setInProgressFilter();
                        break;
                    case SUBMITTED:
                        presenter.setSubmittedFilter();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void updateNewAuditFilterUI() {
        listHeaderDate.setText(R.string.text_new);
    }

    @Override
    public void updateInProgressFilterUI() {
        listHeaderDate.setText(R.string.text_started);
    }

    @Override
    public void updateSubmittedFilterUI() {
        listHeaderDate.setText(R.string.text_submitted);
    }

    @Override
    public void setBarTitleTemplatesList() {
        ((DashboardActivity)getActivity()).setBarTitleTemplatesList();
    }

    @Override
    public void replaceMainContentFragmentWithTemplatesList() {

        ((DashboardActivity)getActivity()).navigateToTemplatePage();
    }

    @OnClick(R.id.my_audits_button_create_audit)
    void loadAuditTemplatesPage() {
        presenter.loadAuditTemplatesPage();
    }
}
