package ru.job4j.generics.store;

/**
 * Интерфейс контейнера для хранения объектов. Принимает какой-то объект (model) класса Base или любого его наследника
 *
 * id - идентификатор элемента в контейнере
 * model - новый элемент добавляемый в контейнер
 *
 * Методы универсального интерфейса контейнера для хранения объектов:
 *  * add - добавление элемента
 *  * replace - замена элемента
 *  * delete - удаление элемента
 *  * findById - поиск элемента по айди
 *
 * @author Alex_life
 * @version 1.0
 * @since 01.11.2021
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
