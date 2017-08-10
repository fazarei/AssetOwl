package com.assetowl.domain.featureToggle.model;

import java.util.List;

/**
 * Created by farzanehzarei on 15/5/17.
 */

public interface FeatureToggleInfo {

    public String getName();

    public String getDisplayName();

    public String getDescription();

    public String getStatus();

    public Boolean getAllowUserOverride();
}
