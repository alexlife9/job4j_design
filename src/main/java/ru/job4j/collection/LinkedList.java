package ru.job4j.collection;

/**
 * Создать контейнер на базе связанного списка
 * add(E value); (добавляет в конец).
 * E get(int index); (перебирает элементы до указанного индекса и возвращает значение из найденной ноды).
 *
 * @author Alex_life
 * @version 1.0
 * @since 04.07.2022
 */
public interface LinkedList<E> extends Iterable<E> {

    void add(E value);

    E get(int index);

}
