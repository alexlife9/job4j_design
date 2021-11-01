package ru.job4j.generics.store;

import java.util.HashMap;
import java.util.Map;

/**
 * MemStore - Каркас универсального хранилища.
 * Его можно использовать для любых классов, наследуемых от Base.
 * RoleStore и UserStore служат как раз для демонстрации этого.
 * Деление на то, что будет храниться в MemStore происходит при наследовании RoleStore, UserStore.
 * MemStore это универсальное хранилище, мы не можем знать, что конкретно будет в нем храниться -
 * можем хранить например людей, некие роли, автомобили, алкоголь, редкие слова, в общем абсолютно всё что угодно.
 *
 * @author Alex_life
 * @version 1.0
 * @since 01.11.2021
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (mem.containsKey(id)) {
            mem.replace(id, model);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (mem.containsKey(id)) {
            mem.remove(id);
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (String key : mem.keySet()) {
            if (key.equals(id)) {
                return mem.get(key);
            }
        }
        return null;
    }
}
