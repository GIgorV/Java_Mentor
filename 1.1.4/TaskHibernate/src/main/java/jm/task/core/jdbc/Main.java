package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJdbcImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        String testName = "Ivan";
        String testLastName = "Ivanov";
        int testAge = 5;
        UserService userJdbc = new UserServiceImpl();
//        userJdbc.createUsersTable();
//        userJdbc.dropUsersTable();
//        userJdbc.saveUser(testName, testLastName, testAge);
//        System.out.println(userJdbc.getAllUsers().get(0));
//        userJdbc.removeUserById(2L);
        userJdbc.cleanUsersTable();
    }
}