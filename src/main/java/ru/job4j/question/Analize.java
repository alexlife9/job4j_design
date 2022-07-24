package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
 * @version 2.0
 * @since 24.07.2022
 */

/**
 * класс Analize анализирует начальное и измененное состояние множества
 */
public class Analize {

    /**
     * метод diff вычисляет кол-во изменений в начальном множестве по трем действиям.
     *
     * @param previous начальное множество юзеров
     * @param current измененное множество юзеров
     * @return объект Info содержащий информацию об изменениях
     *
     * 1.задаем счетчики действий - добавление, изменение и удаление юзера
     * 2.добавляем в хэшмапу всех юзеров которые были в начальном множестве
     *  для этого проходим циклу по начальному множеству юзеров
     *  и добавляем в хэшмапу элементы "идентификатор + имя"
     * 3.делаем тоже самое для изменненого множества
     * 4.еще одним циклом проходим по хэшмапе prevMap c начальными элементами и ищем отличия с хэшпамой currMap
     *  для этого сравниваем все элементы на изменения состояния
     * 5.если ключ элемента был в начальной карте prevMap, но в измененной карте currMap равен null, это значит что
     * данный элемент удален - увеличиваем счетчик удалений юзеров.
     * 6.если во множестве ключей карты currMap содержится такой же ключ в карте prevMap,
     * то проверяем равенство значений по этим ключам в обеих картах и если они НЕ равны - значит элемент был изменен.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int addUser = 0;
        int changeUser = 0;
        int deleteUser = 0;
        Map<Integer, String> prevMap = new HashMap<>();
        for (User userPrev : previous) {
            prevMap.put(userPrev.getId(), userPrev.getName());
        }
        Map<Integer, String> currMap = new HashMap<>();
        for (User userCurr : current) {
            currMap.put(userCurr.getId(), userCurr.getName());
        }

        for (Map.Entry<Integer, String> mapPass : prevMap.entrySet()) {
            if (currMap.get(mapPass.getKey()) == null) {
                deleteUser++;
            }
            if (currMap.containsKey(mapPass.getKey()) && !currMap.containsValue(mapPass.getValue())) {
                changeUser++;
            }
        }
        addUser = current.size() - previous.size() + deleteUser;
        return new Info(addUser, changeUser, deleteUser);

    }

}

