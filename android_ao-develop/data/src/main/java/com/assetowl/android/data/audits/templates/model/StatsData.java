package com.assetowl.android.data.audits.templates.model;

import com.assetowl.domain.audits.templates.model.Stats;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by patrickyin on 16/5/17.
 */

public class StatsData extends RealmObject implements Stats {

    @SerializedName("totalQuestions")
    @Expose
    private Integer totalQuestions;

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public String getTotalQuestionsString() {
        return String.valueOf(totalQuestions);
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

}
