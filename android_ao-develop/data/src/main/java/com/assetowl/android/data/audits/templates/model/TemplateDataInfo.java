package com.assetowl.android.data.audits.templates.model;

import com.assetowl.domain.audits.templates.model.TemplateInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by patrickyin on 10/5/17.
 */

public class TemplateDataInfo extends RealmObject implements TemplateInfo {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("version")
    @Expose
    private int version;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("estimatedCompletionMins")
    @Expose
    private long estimatedCompletionMins;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updatedAt")
    @Expose
    private long updatedAt;
    @SerializedName("dueDate")
    @Expose
    private long dueDate;
    @SerializedName("createdByUserId")
    @Expose
    private int createdByUserId;
    @SerializedName("stats")
    @Expose
    private StatsData stats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getIdString() {
        return String.valueOf(id);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEstimatedCompletionMins() {
        return estimatedCompletionMins;
    }

    public void setEstimatedCompletionMins(int estimatedCompletionMins) {
        this.estimatedCompletionMins = estimatedCompletionMins;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public StatsData getStats() {
        return stats;
    }

    public void setStats(StatsData stats) {
        this.stats = stats;
    }
}
