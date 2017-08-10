package com.assetowl.android.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by patrickyin on 22/2/17.
 */
public class StringUtilsTest {

    @Test
    public void emptyStringShouldBeEmptyString() {
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void nullStringShouldBeEmptyString() {
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void stringShouldNotBeEmptyString() {
        assertFalse(StringUtils.isEmpty("not empty"));
    }

}