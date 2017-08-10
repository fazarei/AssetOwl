package com.assetowl.android.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.IdRes;

/**
 * Created by patrickyin on 8/5/17.
 */

public final class FragmentUtils {
    public static void replaceFragment(FragmentManager fragmentManager, @IdRes int contentResourceId, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(contentResourceId, fragment);
        transaction.commit();
    }
}
