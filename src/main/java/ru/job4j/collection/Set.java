package ru.job4j.collection;

/**
 * Реализация коллекции Set на массиве
 *
 * @author Alex_life
 * @version 1.0
 * @since 11.07.2022
 */
public interface Set<T> extends Iterable<T> {
    boolean add(T value);
    boolean contains(T value);
}
