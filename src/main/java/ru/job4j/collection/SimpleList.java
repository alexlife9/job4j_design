package ru.job4j.collection;

/**
 * Динамический список на массиве
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.07.2022
 */
public interface SimpleList<T> extends Iterable<T> {

    void add(T value);

    T set(int index, T newValue);

    T remove(int index);

    T get(int index);

    int size();
}
