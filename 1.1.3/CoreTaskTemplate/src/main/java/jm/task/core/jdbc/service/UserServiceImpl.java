package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJdbcImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.dropUsersTable();
    }

    public void saveUser(String name, String lastName, int age) {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        return userDaoJdbc.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.cleanUsersTable();

    }
}
