package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
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
 * @author Alex_life
 * @version 1.0
 * @since 19.08.2022
 */
public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        Config config = new Config("C:\\Projects\\job4j_design\\src\\main\\resources\\statement.properties");
        config.load();
        try {
            connection = DriverManager.getConnection(
                    config.value("url"),
                    config.value("login"),
                    config.value("password")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * createTable() – создает пустую таблицу без столбцов с указанным именем
     * @param tableName
     */
    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists %s();",
                    tableName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * dropTable() – удаляет таблицу по указанному имени
     * @param tableName
     */
    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "drop table %s;",
                    tableName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * addColumn() – добавляет столбец в таблицу
     * @param tableName
     * @param columnName
     * @param type
     */
    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s add column %s %s;",
                    tableName, columnName, type
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * dropColumn() – удаляет столбец из таблицы
     * @param tableName
     * @param columnName
     */
    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s drop column %s;",
                    tableName, columnName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * renameColumn() – переименовывает столбец
     * @param tableName
     * @param columnName
     * @param newColumnName
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s rename column %s to %s;",
                    tableName, columnName, newColumnName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("C:\\Projects\\job4j_design\\src\\main\\resources\\statement.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("create_test");
            System.out.println(getTableScheme(tableEditor.connection, "create_test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
