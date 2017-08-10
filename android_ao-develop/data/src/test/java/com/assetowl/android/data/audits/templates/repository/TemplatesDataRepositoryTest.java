package com.assetowl.android.data.audits.templates.repository;

import com.assetowl.android.data.audits.templates.api.Templates;
import com.assetowl.android.data.audits.templates.model.TemplateDataInfo;
import com.assetowl.android.data.localstorage.SessionLocalData;
import com.assetowl.android.data.localstorage.utils.LocalStorageHelper;
import com.assetowl.android.data.login.model.realm.LocalSessionInfo;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by patrickyin on 15/5/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Retrofit.class, JSONObject.class, SessionLocalData.class, LocalStorageHelper.class, Func1.class, AndroidSchedulers.class})
public class TemplatesDataRepositoryTest {

    @Mock
    Retrofit retrofit;

    @Mock
    private Observer<List<TemplateDataInfo>> observer;

    @Mock
    Templates templates;

    @Mock
    Observable<List<TemplateDataInfo>> observable;

    @Mock
    LocalSessionInfo localSessionInfo;

    @Mock
    Scheduler scheduler;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(SessionLocalData.class);
        PowerMockito.mockStatic(LocalStorageHelper.class);
        PowerMockito.mockStatic(AndroidSchedulers.class);
        when(AndroidSchedulers.mainThread())
                .thenReturn(scheduler);
        when(SessionLocalData.fetchSessionInfoFromStorage())
                .thenReturn(localSessionInfo);
        when(localSessionInfo.getAccessToken())
                .thenReturn("");
        when(retrofit.create(any(Class.class)))
                .thenReturn(templates);
    }

    @Test
    public void shouldReturnATemplatesListObservable() throws Exception {
        when(templates.getTemplatesList())
                .thenReturn(observable);
        TemplatesDataRepository templatesDataRepository = new TemplatesDataRepository(retrofit);
        assertTrue(templatesDataRepository.templatesList() != null);
    }
}