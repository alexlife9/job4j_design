package ru.job4j.collection;

/**
 * Используя контейнер на базе связанного списка создать контейнер Stack
 *
 * @author Alex_life
 * @version 2.0
 * 1. добавил метод isEmpty
 * @since 10.07.2022
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * метод pop возвращает значение первого элемента и удаляет элемент из стека
     * @return возвращает удаленный элемент
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * метод push добавляет значение элемента в стек
     * @param value элемент стека
     */
    public void push(T value) {
        linked.addFirst(value);
    }

    /**
     * метод проверяет пустая ли у нас коллекция
     * @return true если в голове коллекции null
     */
    public boolean isEmpty() {
        return linked.isEmpty();
    }
}
