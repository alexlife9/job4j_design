package ru.job4j.jdbc;

import java.sql.*;
import java.util.StringJoiner;
/**
 * Statement
 * Для выполнения операций с БД существуют специальные интерфейсы: Statement, PreparedStatement
 *
 * @author Alex_life
 * @version 1.0
 * @since 19.08.2022
 */
public class StatementDemo {

    /**
     * создаем подключение к БД
     * @return созданное подключение с указанными параметрами
     * @throws Exception
     */
    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, login, password);
    }

    /**
     * INFO:
     * executeUpdate() - метод используется как для выполнения операторов управления данными (DML - операторы),
     * например INSERT, UPDATE или DELETE, так и для операторов определения структуры базы данных (DDL - операторы),
     * например CREATE TABLE, DROP TABLE.
     * Возвращает int – количество affected строк, т.е. количество строк на которые оказал влияние запрос.
     * Для операторов, которые не манипулируют строками, таких как CREATE TABLE или DROP TABLE,
     * возвращаемое значение executeUpdate всегда равно нулю.
     *
     * executeQuery() - rак правило, этот метод используется для выполнения операции SELECT
     * и возвращает объект ResultSet, который позволяет пройтись по результатам запроса.
     *
     * execute() - метод используется для выполнения любых команд.
     * Возвращает true, если результатом выполнения является ResultSet (то есть был выполнен SELECT запрос)
     * или false, если результатом является int (количество изменённых строк).
     * Получить ResultSet или количество строк можно с помощью последующего вызова getUpdateCount() или getResultSet().
     *
     * Например:
     * statement.execute(sql);
     * int count = statement.getUpdateCount();
     * эквивалентно
     * int count = statement.executeUpdate(sql);
     *
     * statement.execute(sql);
     * ResultSet result = statement.getResultSet();
     * эквивалентно
     * ResultSet result = statement.executeQuery(sql);
     */
    public static void main(String[] args) throws Exception {
        /**
         * запрос на подключение к БД
         */
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) { /* создали объект для запроса.
                                   Для его выполнения существуют 3 метода: execute(), executeUpdate(), executeQuery()*/
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql); /* для создания таблицы применяем метод execute() */
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    /**
     * Проверяем, что таблица создалась. Выводим ее схему, а именно ее столбцы и их типы.
     *
     * @param connection
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

}

