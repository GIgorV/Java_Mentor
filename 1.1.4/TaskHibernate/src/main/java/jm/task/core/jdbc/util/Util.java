package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.MetadataSourceType;
import org.hibernate.service.ServiceRegistry;
import org.springframework.security.core.parameters.P;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    //В класс Util должна быть добавлена конфигурация для Hibernate ( рядом с JDBC), без использования xml.
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL, DB_URL);
                settings.put(Environment.USER, DB_USER);
                settings.put(Environment.PASS, DB_PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.SHOW_SQL, "true");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
            }
        }
        return sessionFactory;
    }
}

//    private static StandardServiceRegistry serviceRegistry;
//    private static SessionFactory sessionFactory;
//static {
//        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
//        Map<String, String> dbSettings = new HashMap<>();
//        dbSettings.put(Environment.URL, DB_URL);
//        dbSettings.put(Environment.USER, DB_USER);
//        dbSettings.put(Environment.PASS, DB_PASSWORD);
//        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
//        dbSettings.put(Environment.HBM2DDL_AUTO, "update");
//        dbSettings.put(Environment.SHOW_SQL, "true");
//
//        registryBuilder.applySettings(dbSettings);
//        serviceRegistry = registryBuilder.build();
//        MetadataSources sources = new MetadataSources(serviceRegistry);
//        Metadata metadata = sources.getMetadataBuilder().build();
//        sessionFactory = metadata.getSessionFactoryBuilder().build();
//        }
//public static SessionFactory getSessionFactory(){
//        return sessionFactory;
//        }
