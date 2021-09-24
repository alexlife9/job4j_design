package ru.job4j.it;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

/**
 * Класс FlatMap принимает объект вложенных итераторов.
 * @param <T>
 * @author Alex_life
 * @version 3.0
 */
public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    private void check() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
        }
    }

    @Override
    public boolean hasNext() {
        check();
        return cursor.hasNext();
    }

    /**
     * @return метод next должен последовательно вернуть числа из вложенных итераторов
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
