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
 * 3. Если переопределить только equals без hashCode, то сначала сравниваются объекты с помощью метода хешкод, который
 * зашит по-умолчанию в Collections.Framework. Согласно этой реализаци разные объекты не равны,
 * даже если их содержимое полностью идентично, соответственно и хешкоды объектов будут при вычислении разные.
 * Поскольку equals переопределен, то объекты будут равны, потому что их содержимое сравнивается уже напрямую исходя из
 * их содержимого, а не просто как сравнение двух ссылок на объекты. Вступает в силу реализация метода от класса Objects.
 *
 * 4. Если переопределить equals и hashCode, то видим что хэшкоды объектов равны и содержимое объектов тоже равно.
 * Значит в нашей хешмапе останется только один объект в одном бакете с заданными уникальными значениями.
 *
 * @author Alex_life
 * @version 4.0
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
