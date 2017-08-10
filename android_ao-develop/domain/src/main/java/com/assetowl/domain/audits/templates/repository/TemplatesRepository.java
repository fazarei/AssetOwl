package com.assetowl.domain.audits.templates.repository;

import com.assetowl.domain.audits.templates.model.TemplateInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by patrickyin on 10/5/17.
 */

public interface TemplatesRepository {
    Observable<List<? extends TemplateInfo>> templatesList();
}
