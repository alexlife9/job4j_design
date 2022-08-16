package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * JDBC
 * Java DataBase Connectivity — соединение с базами данных на Java.
 * Предназначен для взаимодействия Java-приложения с различными системами управления базами данных (СУБД).
 * Всё движение в JDBC основано на драйверах которые указываются специально описанным URL.
 * @author Alex_life
 * @version 1.0
 * @since 17.08.2022
 */
public class ConnectionDemo {
    /**
     * 1.Регистрируем драйвер
     * 2.Для подключения к БД нам нужны url, логин (имя пользователя) и пароль.
     * Чтобы получить подключение нужно воспользоваться классом DriverManager, передав ему эти аргументы.
     * 3.в url стоит префикс “jdbc:postgres”. Это указывает, что мы подключаемся к postgres через jdbc.
     * Далее как обычно идет хост и порт, а за ними уже имя базы данных.
     * 4.Мы получили объект типа Connection.
     * Если он не равен null, то это значит, что установлено подключение и можно выполнять запросы к базе данных.
     * 5.Теперь получим имя пользователя и url, а затем их выведем.
     * Для получения информации о БД и ее внутренней структуре существует класс DatabaseMetaData.
     * Через него мы можем получить нужные данные.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
