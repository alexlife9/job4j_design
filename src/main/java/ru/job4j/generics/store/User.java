package ru.job4j.generics.store;
/**
 * Например, можно хранить пользователей в MemStore
 * User - модель пользователя
 *
 * @author Alex_life
 * @version 2.0
 * @since 02.11.2021
 */
public class User extends Base {
    public User(String id) {
        super(id);
    }

    public String getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return " UId:" + getId();
    }
}