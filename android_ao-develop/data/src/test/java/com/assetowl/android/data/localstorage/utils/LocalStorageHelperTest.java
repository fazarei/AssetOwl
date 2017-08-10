package com.assetowl.android.data.localstorage.utils;

import com.assetowl.android.data.audits.templates.model.TemplateDataInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.log.RealmLog;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by patrickyin on 15/5/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Realm.class, RealmLog.class, RealmQuery.class, RealmResults.class})
public class LocalStorageHelperTest {
    @Mock
    Realm realm;

    @Mock
    RealmResults<TemplateDataInfo> realmResults;

    @Mock
    RealmQuery realmQuery;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Realm.class);
        PowerMockito.mockStatic(RealmResults.class);
        when(Realm.getDefaultInstance()).thenReturn(this.realm);
    }

    @Test
    public void shouldReplaceRealmDataWhenGivenAList() throws Exception {
        List<TemplateDataInfo> list = new ArrayList<>();
        LocalStorageHelper.replace(TemplateDataInfo.class, list);
        verify(realm).beginTransaction();
        verify(realm).delete(any(Class.class));
        verify(realm).createAllFromJson(any(Class.class), anyString());
        verify(realm).commitTransaction();
    }

    @Test
    public void shouldReturnAllDataAsAnArrayListWhenInvokeFetchAllMethod() throws Exception {
        TemplateDataInfo[] templateDataInfos = new TemplateDataInfo[1];
        templateDataInfos[0] = new TemplateDataInfo();
        when(realm.where(any(Class.class)))
                .thenReturn(realmQuery);
        when(realmQuery.findAll())
                .thenReturn(realmResults);
        when(realmResults.toArray())
                .thenReturn(templateDataInfos);
        List result = LocalStorageHelper.fetchAll(TemplateDataInfo.class);
        assertEquals(1, result.size());
    }
}