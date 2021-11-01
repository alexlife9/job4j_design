package ru.job4j.generics.store;

/**
 * Реализация хранения ролей
 * add - добавление роли
 * replace - замена роли
 * delete - удаление роли
 * findById - поиск роли по айди
 *
 * @author Alex_life
 * @version 1.0
 * @since 01.11.2021
 */
public class RoleStore implements Store<Role> {
    private final Store<Role> roleStore = new MemStore<>();

    @Override
    public void add(Role model) {
        roleStore.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return roleStore.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return roleStore.delete(id);
    }

    @Override
    public Role findById(String id) {
        return roleStore.findById(id);
    }
}
