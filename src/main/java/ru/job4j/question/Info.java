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
public class Info {

    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public int getAdded() {
        return added;
    }

    public int getChanged() {
        return changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;
        return added == info.added && changed == info.changed && deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(added, changed, deleted);
    }
}

