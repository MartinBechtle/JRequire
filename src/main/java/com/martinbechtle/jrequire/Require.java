package com.martinbechtle.jrequire;

import java.util.Collection;
import java.util.function.Supplier;

import static java.lang.Boolean.TRUE;

/**
 * Utility class for specifying requirements on method arguments.
 * @author Martin Bechtle
 */
public class Require {

    private static final String CANNOT_BE_EMPTY = "Cannot be empty";
    private static final String CANNOT_BE_NULL = "Cannot be null";

    private Require() { }

    /**
     * Returns the argument if not null
     * @param object the variable to be checked
     * @throws IllegalArgumentException if argument is null
     */
    public static <T> T notNull(T object) throws IllegalArgumentException {
        if (object == null) {
            illegalArgument(CANNOT_BE_NULL);
        }
        return object;
    }

    /**
     * Returns the argument if not null
     * @param object the variable to be checked
     * @param name the name of the variable to be displayed in the error message
     * @throws IllegalArgumentException if argument is null
     */
    public static <T> T notNull(T object, String name) throws IllegalArgumentException {
        if (object == null) {
            illegalArgument(CANNOT_BE_NULL, name);
        }
        return object;
    }

    /**
     * Returns the argument if not empty
     * @param str the string to be checked
     * @throws IllegalArgumentException if argument is null or empty
     */
    public static String notEmpty(String str) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            illegalArgument(CANNOT_BE_EMPTY);
        }
        return str;
    }

    /**
     * Returns the argument if not empty
     * @param str the string to be checked
     * @param name the name of the variable to be displayed in the error message
     * @throws IllegalArgumentException if argument is null or empty
     */
    public static String notEmpty(String str, String name) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            illegalArgument(CANNOT_BE_EMPTY, name);
        }
        return str;
    }

    /**
     * Returns the argument if not empty
     * @param collection the collection to be checked
     * @param name the name of the variable to be displayed in the error message
     * @throws IllegalArgumentException if argument is null or empty
     */
    public static <T> Collection<T> notEmpty(Collection<T> collection, String name) throws IllegalArgumentException {
        if (isEmpty(collection)) {
            illegalArgument(CANNOT_BE_EMPTY, name);
        }
        return collection;
    }

    /**
     * Returns the argument if not empty
     * @param collection the collection to be checked
     * @throws IllegalArgumentException if argument is null or empty
     */
    public static <T> Collection<T> notEmpty(Collection<T> collection) throws IllegalArgumentException {
        if (isEmpty(collection)) {
            illegalArgument(CANNOT_BE_EMPTY);
        }
        return collection;
    }

    /**
     * Returns the argument if not empty
     * @param array the array to be checked
     * @param name the name of the variable to be displayed in the error message
     * @throws IllegalArgumentException if argument is null or empty
     */
    public static <T> T[] notEmpty(T[] array, String name) throws IllegalArgumentException {
        if (isEmpty(array)) {
            illegalArgument(CANNOT_BE_EMPTY, name);
        }
        return array;
    }

    /**
     * Returns the argument if not empty
     * @param array the array to be checked
     * @throws IllegalArgumentException if argument is null or empty
     */
    public static <T> T[] notEmpty(T[] array) throws IllegalArgumentException {
        if (isEmpty(array)) {
            illegalArgument(CANNOT_BE_EMPTY);
        }
        return array;
    }

    /**
     * Verify a condition, requiring it to be true
     * @param condition the condition to be checked
     * @param msg an error message to be presented in case of failing condition
     * @throws IllegalArgumentException if condition is false
     */
    public static void verify(boolean condition, String msg) throws IllegalArgumentException {
        if (!condition) {
            illegalArgument(msg);
        }
    }

    /**
     * Verify a condition, requiring it to be true
     * @param conditionSupplier the condition to be checked
     * @param msg an error message to be presented in case of failing condition
     * @throws IllegalArgumentException if condition is false
     */
    public static void verify(Supplier<Boolean> conditionSupplier, String msg) throws IllegalArgumentException {
        if (!TRUE.equals(conditionSupplier.get())) {
            illegalArgument(msg);
        }
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    private static void illegalArgument(String msg) {
        throw new IllegalArgumentException(msg);
    }

    private static void illegalArgument(String msg, String name) {
        throw new IllegalArgumentException(msg + ": " + name);
    }
}
