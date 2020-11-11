package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    // открываем сессию для работы с БД
    Session session = getSessionFactory().openSession();
    // открываем транзакцию (выполняется либо полностью, либо не выполняется)
    Transaction transaction = null;

    @Override
    public void saveUser(String name, String lastName, int age) {
        transaction = session.beginTransaction();
        User user = User.builder().name(name).lastName(lastName).age(age).build();
        session.persist(user); // сохраянем его в БД
        session.clear(); // чтобы брать пользователя не из кэша, а из БД.
        transaction.commit(); // завершили транзакцию
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        transaction = session.beginTransaction();
        User persistentInstance = session.load(User.class, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Query<User> usersQuery = session.createQuery("from User");
        return usersQuery.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public UserDaoHibernateImpl() {
    }

//Методы создания и удаления таблицы пользователей
// в классе UserHibernateDaoImpl должны быть реализованы с помощью SQL.

    private final Connection connection = getConnection();

    @Override
    public void createUsersTable() {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("create table service_user (\n" +
                    "    id         serial primary key,\n" +
                    "    first_name char(20),\n" +
                    "    last_name  char(20),\n" +
                    "    age        integer\n" +
                    "    );");
            statement.execute();
        } catch (SQLException e) {
        }
    }

    @Override
    public void dropUsersTable() {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("drop table service_user");
            statement.execute();
        } catch (SQLException e) {
        }
    }
}

/*
https://hibernate.org/orm/
https://www.cyberforum.ru/java-database/thread1051667.html
свежий гайд о конфиге без xml
* https://www.sourcecodeexamples.net/2019/11/hibernate-java-configuration-without-xml-example-in-eclipse-using-maven.html
базовый гайд по хиберу
https://www.tutorialspoint.com/hibernate/index.htm
хотя лучше воспользываться этим ресурсом тут более преближенно к тому что пишут в оф-й доке)
https://www.baeldung.com/hibernate-5-bootstrapping-api
Util подробно:
https://www.boraji.com/hibernate-5-remove-an-entity-example#:~:text=In Hibernate, an entity can,or persistent object from datastore.
создание коннекта без XML файла!
https://www.youtube.com/watch?v=luB6Ru08y-Q
для начала пойдет - https://metanit.com/java/database/1.1.php - JDBC
https://www.tutorialspoint.com/hibernate/hibernate_sessions.htm
https://javarush.ru/groups/posts/hibernate-java
 */
