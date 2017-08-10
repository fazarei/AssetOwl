package com.assetowl.android.data.featureToggle.model;

import com.assetowl.domain.featureToggle.model.FeatureToggleInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by farzanehzarei on 12/5/17.
 */

public class FeatureToggle extends RealmObject implements FeatureToggleInfo {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("displayName")
    @Expose
    private String displayName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("allowUserOverride")
    @Expose
    private Boolean allowUserOverride;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAllowUserOverride() {
        return allowUserOverride;
    }

    public void setAllowUserOverride(Boolean allowUserOverride) {
        this.allowUserOverride = allowUserOverride;
    }
}
