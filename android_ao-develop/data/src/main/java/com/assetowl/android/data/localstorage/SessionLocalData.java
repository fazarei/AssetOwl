package com.assetowl.android.data.localstorage;

import com.assetowl.android.data.login.model.realm.LocalSessionInfo;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Nima Azimi on 24/04/2017.
 */

public class SessionLocalData {

    public static LocalSessionInfo fetchSessionInfoFromStorage() {
        LocalSessionInfo localSessionInfo = null;

        Realm realm = Realm.getDefaultInstance();
        RealmResults<LocalSessionInfo> localSessionInfoRealmResults = realm.where(LocalSessionInfo.class).findAll();

        if (localSessionInfoRealmResults.size() == 1) {
            localSessionInfo = localSessionInfoRealmResults.get(0);
        }

        return localSessionInfo;
    }
}
