package ru.job4j.generics.store;

/**
 * Например, можно хранить некие Роли в MemStore
 * Role - модель ролей
 *
 * @author Alex_life
 * @version 2.0
 * @since 02.11.2021
 */
public class Role extends Base {
    public Role(String id) {
        super(id);
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
