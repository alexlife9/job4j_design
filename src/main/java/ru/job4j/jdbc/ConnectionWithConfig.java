package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC
 * Java DataBase Connectivity — соединение с базами данных на Java.
 * Предназначен для взаимодействия Java-приложения с различными системами управления базами данных (СУБД).
 * Всё движение в JDBC основано на драйверах которые указываются специально описанным URL.
 *
 * @author Alex_life
 * @version 1.0
 * @since 17.08.2022
 */
public class ConnectionWithConfig {
    /**
     * 1.Для регистрации драйвера передаем в Подключение данные которые читаем из файла app.properties, который
     *   обрабатывается методом load, реализованный в классе Config
     * 2.Для подключения к БД, в файле app.propertiesДля прописаны url, логин и пароль.
     * 3.Если объект типа Connection не равен null - значит, что установлено подключение
     *   и можно выполнять запросы к базе данных.
     * 4.Для получения информации о БД и ее структуре передаем результаты подключения в класс DatabaseMetaData
     *   И с его помощью получаем имя пользователя и url, а затем выводим их в консоль.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("app.properties");
        config.load();
        try (Connection connection = DriverManager.getConnection(config.value("url"), config.value("login"),
                config.value("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
