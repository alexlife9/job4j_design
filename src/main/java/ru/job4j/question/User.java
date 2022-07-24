package ru.job4j.question;

import java.util.Objects;

/**
 * Статистика по коллекции
 * Задание сводится к определению разницы между начальным и измененным состояниями множества,
 * где элементами являются пользователи, у которых есть идентификатор и имя.
 *
 * Существует три действия: добавили пользователя, удалили пользователя, изменили имя пользователя.
 * Примечание. Два пользователя считаются равными, если у них равны идентификаторы и имена.
 * Необходимо вычислить:
 *  - Сколько добавлено новых пользователей. Добавленным считается такой пользователь,
 *  что ранее его не было в множестве previous, но во множестве current он есть.
 *  - Сколько изменено пользователей. Изменённым считается объект, в котором изменилось имя, а id осталось прежним.
 *  - Сколько удалено пользователей. Удаленным считается такой пользователь,
 *  что ранее он был в множестве previous, но теперь во множестве current его нет.
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.07.2022
 */
public class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

