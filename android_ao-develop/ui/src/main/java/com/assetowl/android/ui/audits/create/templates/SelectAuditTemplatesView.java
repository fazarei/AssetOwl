package com.assetowl.android.ui.audits.create.templates;

import com.assetowl.domain.audits.templates.model.TemplateInfo;

import java.util.List;

/**
 * Created by patrickyin on 5/5/17.
 */

public interface SelectAuditTemplatesView {
    void initialiseView();

    void setData(List<? extends TemplateInfo> templatesListData);
}
