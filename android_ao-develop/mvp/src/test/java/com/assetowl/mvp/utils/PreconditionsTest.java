package com.assetowl.mvp.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jamespott on 14/2/17.
 */
public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void nullReferenceShouldThrowNullPointerException() {
        Preconditions.checkNotNull(null);
    }

    @Test
    public void nonNullReferenceShouldReturnReference() {
        Object reference  = new Object();
        Object actual = Preconditions.checkNotNull(reference);
        assertEquals(reference, actual);
    }

    @Test
    public void nullReferencShouldThrowNullPointerExceptionWithExpectedErrorMessage() {
        final String expectedMessage = "expected message";
        try {
            Preconditions.checkNotNull(null, expectedMessage);
        } catch(NullPointerException npe) {
            assertTrue(npe.getMessage() == expectedMessage);
        }
    }

}