package com.assetowl.android.data.localstorage.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by patrickyin on 12/5/17.
 */

public final class LocalStorageHelper {
    public static void replace(final Class<? extends RealmModel> clazz, final List<? extends RealmObject> list) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(clazz);
        realm.createAllFromJson(clazz, new Gson().toJson(list));
        realm.commitTransaction();
    }

    public static List fetchAll(Class<? extends RealmModel> clazz) {
        Realm realm = Realm.getDefaultInstance();
        return new ArrayList<>(Arrays.asList(realm.where(clazz).findAll().toArray()));
    }
}
