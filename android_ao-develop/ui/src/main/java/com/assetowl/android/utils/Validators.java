package com.assetowl.android.utils;

import java.util.regex.Pattern;

/**
 * Created by patrickyin on 17/2/17.
 */

public final class Validators {

    private final static Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public final static boolean validatePassword(String password) {
        if(password == null || password.length() < 9 || password.length() > 256) {
            return false;
        } else {
            return true;
        }
    }

    public final static boolean validateUsername(String username) {
        if (username == null || username.length() > 256 || !VALID_EMAIL_REGEX.matcher(username).find()) {
            return false;
        } else {
            return true;
        }
    }
}
