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
//        UserDaoJdbcImpl userJdbc = new UserDaoJdbcImpl();
        UserService userJdbc = new UserServiceImpl();
//        userJdbc.createUsersTable();
//        userJdbc.dropUsersTable();
//        userJdbc.dropUsersTable();
//        userJdbc.saveUser(testName, testLastName, testAge);
        System.out.println(userJdbc.getAllUsers().get(0));
//        userJdbc.removeUserById(1L);
//        userJdbc.cleanUsersTable();
    }
}
//https://proselyte.net/tutorials/jdbc/introduction/
//https://www.youtube.com/watch?v=nfpq6-bvDcc&list=PLzjEWSim5GogAlVDQXyTkp5j8MpWKtvov&index=4&t=1268s
