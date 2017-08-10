package com.assetowl.android.utils;

/**
 * Created by jamespott on 3/2/17.
 */

public class StringUtils {

    /**
     * Tread null and empty string as empty
     * @param value
     * @return true if null or empty. False otherwise
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
