package ru.job4j.iterator;

import java.util.Iterator;

/**
 * @author Alex_life
 * @version 1.0
 * @since 20.08.2022
 */

public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        return data[point++];
    }
}
