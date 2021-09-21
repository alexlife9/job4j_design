package ru.job4j.it;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < data.length / 2; i++) {
            int temp = data[i];
            data[i] = data[data.length - (i + 1)];
            data[data.length - (i + 1)] = temp;
        }
        return data[point++];
    }
}

