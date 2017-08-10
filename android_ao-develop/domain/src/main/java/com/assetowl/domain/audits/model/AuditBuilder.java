package com.assetowl.domain.audits.model;

import com.assetowl.domain.audits.property.model.PropertyListBuilder;
import com.assetowl.domain.audits.templates.model.AuditTemplateListBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patrickyin on 7/6/17.
 */

public class AuditBuilder implements AuditTemplateListBuilder, PropertyListBuilder, NewAuditBuilder<Collection<Audit>> {
    private Set<Integer> auditTemplates;
    private Set<Integer> auditProperties;

    @Override
    public AuditBuilder addTemplate(Integer templateId) {
        if(auditTemplates == null) {
            auditTemplates = new HashSet<>();
        }
        auditTemplates.add(templateId);
        return this;
    }

    @Override
    public AuditBuilder removeTemplate(Integer templateId) {
        if(auditTemplates != null) {
            auditTemplates.remove(templateId);
        }
        return this;
    }

    @Override
    public AuditBuilder addProperty(Integer propertyId) {
        if(auditProperties == null) {
            auditProperties = new HashSet<>();
        }
        auditProperties.add(propertyId);
        return this;
    }

    @Override
    public AuditBuilder removeProperty(Integer propertyId) {
        if(auditProperties != null) {
            auditProperties.remove(propertyId);
        }
        return this;
    }

    @Override
    public Collection<Audit> build() {
        return null;
    }
}
