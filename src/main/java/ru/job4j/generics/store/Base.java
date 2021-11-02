package ru.job4j.generics.store;

/**
 * Базовая модель объекта, который хранится в контейнере.
 * Объект класса Base - это все что угодно в хранилище со своим id.
 *
 * getId - возвращает идентификатор объекта.
 *
 * @author Alex_life
 * @version 2.0
 * @since 02.11.2021
 */
public abstract class Base {
    private final String id;

    public Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Base{"
                + "id='" + id + '\''
                + '}';
    }
}
