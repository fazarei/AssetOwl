package com.assetowl.android.data.audits.templates.repository;

import com.assetowl.android.data.audits.templates.api.Templates;
import com.assetowl.android.data.audits.templates.model.TemplateDataInfo;
import com.assetowl.android.data.localstorage.utils.LocalStorageHelper;
import com.assetowl.domain.audits.templates.model.TemplateInfo;
import com.assetowl.domain.audits.templates.repository.TemplatesRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by patrickyin on 10/5/17.
 */

public class TemplatesDataRepository implements TemplatesRepository{

    private final Templates authService;

    @Inject
    TemplatesDataRepository(Retrofit retrofit) {
        authService = retrofit.create(Templates.class);
    }

    @Override
    public Observable<List<? extends TemplateInfo>> templatesList() {
        return authService.getTemplatesList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<List<TemplateDataInfo>, List<? extends TemplateInfo>>() {
                    @Override
                    public List<? extends TemplateInfo> call(List<TemplateDataInfo> templateDataInfo) {
                        LocalStorageHelper.replace(TemplateDataInfo.class, templateDataInfo);
                        return templateDataInfo;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, List<? extends TemplateInfo>>() {
                    @Override
                    public List<? extends TemplateInfo> call(Throwable throwable) {
                        return LocalStorageHelper.fetchAll(TemplateDataInfo.class);
                    }
                });
    }
}
