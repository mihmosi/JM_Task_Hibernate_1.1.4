package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

/* Класс Util должен содержать логику настройки соединения с базой данных */
public class Util {
    //  определяем константы
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";

    private final static String URL =
            "jdbc:mysql://127.0.0.1:3306/testbase?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Lelechka3003";

    public Util() {
    }

    // HiBerNate============================================

    private static SessionFactory sessionFactory;

    static {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.driver_class", DB_DRIVER);
        prop.setProperty("dialect", DB_DIALECT);
        prop.setProperty("hibernate.connection.url", URL);
        prop.setProperty("hibernate.connection.username", LOGIN);
        prop.setProperty("hibernate.connection.password", PASSWORD);
        prop.setProperty("hbm2ddl.auto", "update");

        prop.setProperty("hibernate.show_sql", "false");
        prop.setProperty("hibernate.format_sql", "false");
        prop.setProperty("hibernate.generate_statistics", "false");
        prop.setProperty("hibernate.use_sql_comments", "false");
        prop.setProperty("connection.provider_class", "org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");
        prop.setProperty("hibernate.c3p0.min_size", "5");
        prop.setProperty("hibernate.c3p0.max_size", "20");
        prop.setProperty("hibernate.c3p0.acquire_increment", "5");
        prop.setProperty("hibernate.c3p0.timeout", "1800");
        prop.setProperty("hibernate.c3p0.connectionCustomizerClassName", "jm.task.core.jdbc.util.ConnectionPoolLogger");
        try {
            sessionFactory = new Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

            //Hibernate setting
//            Map<String, String> dbSettings = new HashMap<>();
//            dbSettings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/users?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true\" +\n" +
//                    "\"&useLegacyDatetimeCode=false&serverTimezone=UTC");
//            dbSettings.put(Environment.USER, "root");
//            dbSettings.put(Environment.PASS, "root");
//            dbSettings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
//            dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

//            //Apply database setting
//            registryBuilder.applySettings(dbSettings);
//            //Creating registry
//            standartServiceRegistry = registryBuilder.build();
//            //Creating MetadataSources
//            MetadataSources sources = new MetadataSources(standartServiceRegistry);
//            //Creating Metadata
//            Metadata metadata = sources.getMetadataBuilder().build();
//            //Creating SessionFactory
//            sessionFactory = metadata.getSessionFactoryBuilder().build();


            //Возвращаем sessionFactory
        } catch (HibernateException he) {
            he.printStackTrace();
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // реализуйте настройку соеденения с БД JDBC==========================

    public Connection getUtilConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            if (!conn.isClosed()) {
                System.out.println("Correct connection to db!  ");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }

}
