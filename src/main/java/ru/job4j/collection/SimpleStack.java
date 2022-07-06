package ru.job4j.collection;

/**
 * Используя контейнер на базе связанного списка создать контейнер Stack
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.07.2022
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
}
