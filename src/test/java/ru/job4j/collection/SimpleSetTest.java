package ru.job4j.collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Реализация коллекции Set на массиве
 * 
 * @author Alex_life
 * @version 1.0
 * @since 11.07.2022
 */
public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddDuplicate() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.add(10));
        assertTrue(set.add(50));
        assertTrue(set.add(90));
        assertTrue(set.contains(null));
        assertTrue(set.contains(10));
        assertFalse(set.contains(55));
        assertFalse(set.add(null));
    }

}
