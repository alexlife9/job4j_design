package ru.job4j.map;

/**
 * Реализация собственной структуры данных - HashMap
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.07.2022
 */
public interface Map<K, V> extends Iterable<K> {
    boolean put(K key, V value);
    V get(K key);
    boolean remove(K key);
}
