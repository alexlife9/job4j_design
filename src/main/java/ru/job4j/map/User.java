package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Alex", 21, birthday);
        User user2 = new User("Alex", 21, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
