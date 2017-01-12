package com.martinbechtle.jrequire;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;

import static com.martinbechtle.jrequire.Require.notEmpty;
import static com.martinbechtle.jrequire.Require.notNull;
import static com.martinbechtle.jrequire.Require.verify;
import static org.junit.Assert.*;

/**
 * Simple unit test for {@link Require}. Using the one test per method convention.
 * @author Martin Bechtle
 */
public class RequireTest {

    final Object NULL_OBJECT = null;
    final String TEST_STRING = " test 123 ";
    final String EMPTY_STRING = "";
    final String NULL_STRING = null;

    @Test
    public void notNullTest() throws Exception {

        assertThrowsIllegalArgument(() -> notNull(NULL_OBJECT), "Cannot be null");
        assertThrowsIllegalArgument(() -> notNull(NULL_OBJECT, "myVar"), "Cannot be null: myVar");
        assertEquals(TEST_STRING, notNull(TEST_STRING));
        assertEquals(EMPTY_STRING, notNull(EMPTY_STRING));
    }

    @Test
    public void notEmptyStringTest() throws Exception {

        assertThrowsIllegalArgument(() -> notEmpty(NULL_STRING), "Cannot be empty");
        assertThrowsIllegalArgument(() -> notEmpty(NULL_STRING, "myOtherVar"), "Cannot be empty: myOtherVar");
        assertThrowsIllegalArgument(() -> notEmpty(EMPTY_STRING), "Cannot be empty");
        assertThrowsIllegalArgument(() -> notEmpty(EMPTY_STRING, "myOtherVar"), "Cannot be empty: myOtherVar");
        assertEquals(TEST_STRING, notEmpty(TEST_STRING));
    }

    @Test
    public void notEmptyCollectionTest() throws Exception {

        List<Integer> nullList = null;
        Set<String> emptySet = new HashSet<>();
        Queue<Double> populatedQueue = new LinkedBlockingDeque<>();
        populatedQueue.add(1.0);

        assertThrowsIllegalArgument(() -> notEmpty(nullList), "Cannot be empty");
        assertThrowsIllegalArgument(() -> notEmpty(emptySet), "Cannot be empty");
        assertThrowsIllegalArgument(() -> notEmpty(emptySet, "mySet"), "Cannot be empty: mySet");
        assertEquals(populatedQueue, notEmpty(populatedQueue));
    }

    @Test
    public void notEmptyArrayTest() throws Exception {

        String[] nullArray = null;
        Integer[] emptyArray = new Integer[] { };
        String[] notEmptyArray = new String[1];

        assertThrowsIllegalArgument(() -> notEmpty(nullArray), "Cannot be empty");
        assertThrowsIllegalArgument(() -> notEmpty(emptyArray), "Cannot be empty");
        assertThrowsIllegalArgument(() -> notEmpty(emptyArray, "myArray"), "Cannot be empty: myArray");
        assertArrayEquals(notEmptyArray, notEmpty(notEmptyArray));
    }

    @Test
    public void verifyBooleanTest() throws Exception {

        assertThrowsIllegalArgument(() -> verify(false, "false"), "false");
        verify(true, "true");
    }

    @Test
    public void verifyBooleanSupplierTest() throws Exception {

        assertThrowsIllegalArgument(() -> verify(() -> false, "false"), "false");
        verify(() -> true, "true");
    }

    private void assertThrowsIllegalArgument(Callable callable, String expectedMessage) {

        try {
            callable.call();
        } catch (Exception e) {
            assertTrue("IllegalArgumentException was expected, instead got " + e.getClass(),
                    e instanceof IllegalArgumentException);
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    private void assertThrowsIllegalArgument(Runnable runnable, String expectedMessage) {

        try {
            runnable.run();
        } catch (Exception e) {
            assertTrue("IllegalArgumentException was expected, instead got " + e.getClass(),
                    e instanceof IllegalArgumentException);
            assertEquals(expectedMessage, e.getMessage());
        }
    }

}