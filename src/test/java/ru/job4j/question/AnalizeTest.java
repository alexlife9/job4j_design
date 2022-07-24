package ru.job4j.question;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
public class AnalizeTest {

    @Test
    public void whenNotChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3);
        assertThat(
                Analize.diff(previous, current),
                is(new Info(0, 0, 0))
        );
    }

    @Test
    public void whenOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, new User(2, "BB"), u3);
        assertThat(
                Analize.diff(previous, current),
                is(new Info(0, 1, 0))
        );
    }

    @Test
    public void whenOneDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u3);
        assertThat(
                Analize.diff(previous, current),
                is(new Info(0, 0, 1))
        );
    }

    @Test
    public void whenOneAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"));
        assertThat(
                Analize.diff(previous, current),
                is(new Info(1, 0, 0))
        );
    }

    @Test
    public void whenAllChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        assertThat(
                Analize.diff(previous, current),
                is(new Info(1, 1, 1))
        );
    }

}
