package com.assetowl.android.data.audits.templates.model;

import com.assetowl.domain.audits.templates.model.TemplateCheckInfo;

/**
 * Created by farzanehzarei on 31/5/17.
 */

public class TemplateCheck implements TemplateCheckInfo{
    private int id;
    private boolean templateCheck;

    public TemplateCheck(int id, boolean templateCheck) {
        this.id = id;
        this.templateCheck = templateCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTemplateCheck() {
        return templateCheck;
    }

    public void setTemplateCheck(boolean templateCheck) {
        this.templateCheck = templateCheck;
    }
}
