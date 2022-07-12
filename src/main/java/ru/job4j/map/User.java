package ru.job4j.map;

import java.util.*;

/**
 * Влияние переопределения equals и hashCode на объекты
 *
 * 1. Если не переопределить equals и hashCode, то идет сравнение методом hashCode "по-умолчанию",по логике которая
 * зашита в класс Collections, при которой разные объекты не равны, даже если их содержимое полностью идентично,
 * соответственно и хешкоды объектов будут при вычислении разные.
 * Метод equals при этом вообще не вызывается, если hashCode разный.
 * Объекты попадают всегда в разные бакеты.
 *
 * 2. Если переопределить только hashCode без equals, то хешкоды объектов с одинаковыми значениями будут равны.
 * Поскольку сравнивали хешкоды, то иквалз сравнивается реализацией по-умолчанию, при которой
 * разные объекты (даже с одинаковыми значениями) конечно же не равны, потому что ссылаются на разные участки памяти
 *
 * @author Alex_life
 * @version 2.0
 * @since 12.07.2022
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Alex", 21,
                new GregorianCalendar(1982, 2, 21, 5, 50, 30));
        User user2 = new User("Alex", 21,
                new GregorianCalendar(1982, 2, 21, 5, 50, 30));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
