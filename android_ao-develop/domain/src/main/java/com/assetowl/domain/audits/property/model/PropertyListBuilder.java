package com.assetowl.domain.audits.property.model;

import com.assetowl.domain.audits.model.AuditBuilder;

/**
 * Created by patrickyin on 7/6/17.
 */

public interface PropertyListBuilder {
    AuditBuilder addProperty(Integer propertyId);

    AuditBuilder removeProperty(Integer propertyId);
}
