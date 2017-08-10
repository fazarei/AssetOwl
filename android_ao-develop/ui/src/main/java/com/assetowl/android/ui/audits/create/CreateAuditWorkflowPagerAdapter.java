package com.assetowl.android.ui.audits.create;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.assetowl.android.ui.audits.create.properties.SelectPropertiesFragment;
import com.assetowl.android.ui.audits.create.templates.SelectAuditTemplatesFragment;

/**
 * Created by patrickyin on 7/6/17.
 */

public class CreateAuditWorkflowPagerAdapter extends FragmentStatePagerAdapter {
    public CreateAuditWorkflowPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new SelectAuditTemplatesFragment();
            case 1:
                return new SelectPropertiesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
