package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoJdbcImpl extends Util implements UserDao {

    private final Connection connection = getConnection();

    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("create table service_user (\n" +
                    "    id         serial primary key,\n" +
                    "    firstName char(20),\n" +
                    "    lastName  char(20),\n" +
                    "    age        integer\n" +
                    "    );");
            statement.execute();
        } catch (SQLException e) {
//            System.err.println("отношение service_user уже существует");
        }
    }

    public void dropUsersTable() {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("drop table service_user");
            statement.execute();
        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
        }
    }

    public void saveUser(String name, String lastName, int age) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("insert into service_user (firstName, lastName, age) values (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
        }
    }

    public void removeUserById(long id) {
        PreparedStatement statement;
        try {
            List<User> temp = getAllUsers();
            for (User user : temp) {
                if (user.getId() == id) {
                    statement = connection.prepareStatement("DELETE FROM service_user WHERE id = ?");
                    statement.setLong(1, id);
                    statement.execute();
                }
            }
        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
        }
    }

    public List<User> getAllUsers() {
        Statement statement;
        List<User> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM service_user;");
            while (resultSet.next()) {
                result.add(User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("firstName"))
                        .lastName(resultSet.getString("lastName"))
                        .age(resultSet.getInt("age"))
                        .build());
                //RowMapper (функциональный interface) никак не связан с сторонней библиотекой lambok.
                //забегая вперед, думал пригодится, что бы не городить огород из resultSet-ов
                //кто же знал, что уже на следующем задании нам будут не нужны ни SQL-запросы, ни resultSet
                //Hibernate имею в виду)
            }
        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
        }
        return result;
    }

    public void cleanUsersTable() {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("TRUNCATE Table service_user;");
            statement.execute();
        } catch (SQLException e) {
//            throw new IllegalArgumentException(e);
        }
    }
}
