package com.assetowl.domain.audits.templates.model;

import com.assetowl.domain.audits.model.AuditBuilder;

/**
 * Created by patrickyin on 7/6/17.
 */

public interface AuditTemplateListBuilder {

    AuditBuilder addTemplate(Integer templateId);
    AuditBuilder removeTemplate(Integer templateId);
}
