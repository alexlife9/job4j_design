package ru.job4j.generics.store;

import java.util.Objects;

/**
 * Реализация хранения пользователя
 * add - добавление пользователя
 * replace - замена пользователя
 * delete - удаление пользователя
 * findById - поиск пользователя по айди
 *
 * @author Alex_life
 * @version 2.0
 * @since 02.11.2021
 */
public class UserStore implements Store<User> {

    private final Store<User> userStore = new MemStore<>();

    @Override
    public void add(User model) {
        userStore.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        userStore.replace(id, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        userStore.delete(id);
        return true;
    }

    @Override
    public User findById(String id) {
        return userStore.findById(id);
    }

    @Override
    public String toString() {
        return "" + userStore;
    }

    public static void main(String[] args) {
        User one = new User("Alex");
        User two = new User("Petr");
        User three = new User("Dima");
        User four = new User("Anna");
        UserStore userStore = new UserStore();
        userStore.add(one);
        userStore.add(two);
        userStore.add(three);
        userStore.add(four);
        System.out.println("Выводим список всех добавленных контейнеров с именами: " + userStore);
        System.out.println("Ищем имя второго юзера: " + userStore.findById(two.getId()));
        System.out.println("Удаляем третьего юзера. Успешно? " + userStore.delete(three.getId()));
        System.out.println("Выводим текущий список контейнеров: " + userStore);
        System.out.println("Какой юзер теперь на третьем месте? " + userStore.findById(three.getId()));
        System.out.println("Меняем имя второго контейнера на имя четвертого юзера: " + userStore.replace("Petr", four));
        System.out.println("Выводим текущий список контейнеров: " + userStore);
    }
}
