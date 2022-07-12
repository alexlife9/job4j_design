package ru.job4j.map;

import java.util.Calendar;

/**
 * @author Alex_life
 * @version 1.0
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
}
