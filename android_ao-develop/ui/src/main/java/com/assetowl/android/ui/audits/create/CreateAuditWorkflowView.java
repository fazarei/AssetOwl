package com.assetowl.android.ui.audits.create;

import android.content.Context;

/**
 * Created by farzanehzarei on 6/6/17.
 */

public interface CreateAuditWorkflowView {
    void initialiseView();
    Context getContext();
    void enableNextButton();
    void deActiveNextButton();
    void initialiseViewPager();
}
