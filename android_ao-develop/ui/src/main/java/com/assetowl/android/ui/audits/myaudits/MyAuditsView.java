package com.assetowl.android.ui.audits.myaudits;

/**
 * Created by farzanehzarei on 10/5/17.
 */

public interface MyAuditsView {
    void initialiseView();

    void initialiseListener();

    void updateNewAuditFilterUI();

    void updateInProgressFilterUI();

    void updateSubmittedFilterUI();

    void setBarTitleTemplatesList();

    void replaceMainContentFragmentWithTemplatesList();
}
