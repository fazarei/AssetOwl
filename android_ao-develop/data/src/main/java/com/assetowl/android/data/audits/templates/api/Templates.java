package com.assetowl.android.data.audits.templates.api;

import com.assetowl.android.data.audits.templates.model.TemplateDataInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by patrickyin on 11/5/17.
 */

public interface Templates {
    @GET("ir/information-requests?permissions=canAnswer&status=ACTIVE")
    Observable<List<TemplateDataInfo>> getTemplatesList();
}
