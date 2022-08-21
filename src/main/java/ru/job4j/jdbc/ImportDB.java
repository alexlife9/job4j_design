package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PreparedStatement
 *
 * @author Alex_life
 * @version 1.0
 * @since 22.08.2022
 */
public class ImportDB implements AutoCloseable {

    private Connection connection;

    private final Properties cfg;

    private final String dump;

    public ImportDB(Properties cfg, String dump) throws Exception {
        this.cfg = cfg;
        this.dump = dump;
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName(cfg.getProperty("driver"));
        connection = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("login"),
                cfg.getProperty("password")
        );
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(line -> {
                String[] array = line.split(";", 2);
                if (array[0].isEmpty() || array[1].isEmpty()) {
                    throw new IllegalArgumentException("name or email not found");
                }
                users.add(new User(array[0], array[1]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users ...")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
