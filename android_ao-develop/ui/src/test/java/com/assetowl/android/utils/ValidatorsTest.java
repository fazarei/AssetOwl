package com.assetowl.android.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by patrickyin on 7/3/17.
 */
public class ValidatorsTest {
    @Test
    public void shouldPassPasswordValidation() throws Exception {
        assertTrue(Validators.validatePassword("         "));
        assertTrue(Validators.validatePassword("  if03   "));
        assertTrue(Validators.validatePassword(" 3fob035f"));
        assertTrue(Validators.validatePassword("3fob035f "));
        assertTrue(Validators.validatePassword("4y8g9freiohvdfhbuweur3ifopjb093rt4093ir"));
        assertTrue(Validators.validatePassword("wkAhtMpuLE2ZktMZBhxFfNVe"));
        assertTrue(Validators.validatePassword("piaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88f"));
        assertTrue(Validators.validatePassword("piaPTZ2}ah>z(98gm[xm99uQxseas?bd BUNw@cdA,R^d+KYmJv8{GMb26JXy88f"));
    }

    @Test
    public void shouldPassUsernameValidation() throws Exception {
        assertTrue(Validators.validateUsername("e@e.ee"));
        assertTrue(Validators.validateUsername("e2@e3.com"));
        assertTrue(Validators.validateUsername("e2@e3.com"));
        assertTrue(Validators.validateUsername("e2@e3.cc"));
        assertTrue(Validators.validateUsername("test@assetowl.com"));
        assertTrue(Validators.validateUsername("tEst@assetowl.com"));
    }

    @Test
    public void shouldNotPassPasswordValidation() throws Exception {
        assertFalse(Validators.validatePassword(""));
        assertFalse(Validators.validatePassword(" "));
        assertFalse(Validators.validatePassword("    "));
        assertFalse(Validators.validatePassword("    derg"));
        assertFalse(Validators.validatePassword(" kfjepf "));
        assertFalse(Validators.validatePassword("f3io#f"));
        assertFalse(Validators.validatePassword("piaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fpiaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fpiaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fpiaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fder"));
    }

    @Test
    public void shouldNotPassUsernameValidation() throws Exception {
        assertFalse(Validators.validateUsername(""));
        assertFalse(Validators.validateUsername(" "));
        assertFalse(Validators.validateUsername("  "));
        assertFalse(Validators.validateUsername("2"));
        assertFalse(Validators.validateUsername("223fegerf3"));
        assertFalse(Validators.validateUsername("223fegerf3@"));
        assertFalse(Validators.validateUsername("223fegerf3@3"));
        assertFalse(Validators.validateUsername("223fegerf3@3."));
        assertFalse(Validators.validateUsername("223fegerf3@3.c"));
        assertFalse(Validators.validateUsername("@312.com"));
        assertFalse(Validators.validateUsername("312.com"));
        assertFalse(Validators.validateUsername(".com"));
        assertFalse(Validators.validateUsername("com"));
    }


}