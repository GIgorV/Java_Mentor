package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "Kwerty007";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/JM_Users";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return connection;
    }
}
