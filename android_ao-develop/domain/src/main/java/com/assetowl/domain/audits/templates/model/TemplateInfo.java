package com.assetowl.domain.audits.templates.model;

/**
 * Created by patrickyin on 10/5/17.
 */

public interface TemplateInfo {
    int getId();
    String getIdString();
    int getVersion();
    String getName();
    String getDescription();
    long getEstimatedCompletionMins();
    long getDueDate();
    Stats getStats();
}
