package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Statement
 *
 * С помощью метода execute принято выполнять DDL операции, в основном для опредения схемы БД - CREATE, DROP
 * Для выполнения DML операций чаще используется executeUpdate для INSERT и DELETE-операций, а также
 * executeQuery для SELECT-операций
 *
 * @author Alex_life
 * @version 3.0
 * @since 21.08.2022
 */
public class TableEditor implements AutoCloseable {

    /**
     * подключение к БД
     */
    private Connection connection;

    /**
     * настройки подключения к БД
     */
    private final Properties properties;

    /**
     * в конструктор передаем properties - файл с уже загруженными настройками из метода initConnection()
     * @param properties файл с настройками
     */
    public TableEditor(Properties properties) throws ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    /**
     * метод для считывания настроек из указанного файла в properties
     */
    private void initConnection() throws ClassNotFoundException {
        Class.forName(properties.getProperty("driver"));
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * метод executeStatement осуществляет одинаковые начала операций с БД
     * 1. подключаемся к БД и передаем управление в Statement
     * 2. метод execute осуществляет операции с БД из указанного в параметрах запроса
     * @param sql в параметры передаем любой SQL-запрос на обработку
     */
    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * createTable() – создает пустую таблицу без столбцов с указанным именем
     * @param tableName имя таблицы
     */
    public void createTable(String tableName) {
        String sqlCreate = String.format(
            "create table if not exists %s();",
            tableName
        );
        executeStatement(sqlCreate);
    }

    /**
     * dropTable() – удаляет таблицу по указанному имени
     *
     * @param tableName имя таблицы
     */
    public void dropTable(String tableName) {
        String sqlDrop = String.format(
            "drop table %s;",
            tableName
        );
        executeStatement(sqlDrop);
    }

    /**
     * addColumn() – добавляет столбец в таблицу
     * с помощью alter table можно вносить изменения в уже существующую таблицу
     * @param tableName имя таблицы
     * @param columnName имя столбца
     * @param type тип столбца
     */
    public void addColumn(String tableName, String columnName, String type) {
        String sqlAddCol = String.format(
                "alter table %s add column %s %s;",
                tableName, columnName, type
        );
        executeStatement(sqlAddCol);
    }

    /**
     * dropColumn() – удаляет столбец из таблицы
     *
     * @param tableName имя таблицы
     * @param columnName имя столбца
     */
    public void dropColumn(String tableName, String columnName) {
        String sqlDropCol = String.format(
            "alter table %s drop column %s;",
            tableName, columnName
        );
        executeStatement(sqlDropCol);
    }

    /**
     * renameColumn() – переименовывает столбец
     * @param tableName имя таблицы
     * @param columnName имя столбца
     * @param newColumnName новое имя столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sqlRenCol = String.format(
                "alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName
        );
        executeStatement(sqlRenCol);
    }

    /**
     * метод getTableScheme отображает в консоли результат SQL-запроса
     * @param tableName имя таблицы
     * @return отображение готового запроса в консоли
     */
    public String getTableScheme(String tableName) throws Exception {
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("statement.properties")) {
            properties.load(in);
        }

        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("test_table");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.addColumn("test_table", "name1_column", "text");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.addColumn("test_table", "name5_column", "text");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.dropColumn("test_table", "name5_column");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.renameColumn("test_table", "name1_column", "name10_column");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.dropTable("test_table");
            System.out.println("test_table is DELETE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
