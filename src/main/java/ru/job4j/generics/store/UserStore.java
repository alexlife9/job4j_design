package ru.job4j.generics.store;

/**
 * Реализация хранения пользователя
 * add - добавление пользователя
 * replace - замена пользователя
 * delete - удаление пользователя
 * findById - поиск пользователя по айди
 *
 * @author Alex_life
 * @version 1.0
 * @since 01.11.2021
 */
public class UserStore implements Store<User> {

    private final Store<User> userStore = new MemStore<>();

    @Override
    public void add(User model) {
        userStore.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return userStore.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return userStore.delete(id);
    }

    @Override
    public User findById(String id) {
        return userStore.findById(id);
    }
}
