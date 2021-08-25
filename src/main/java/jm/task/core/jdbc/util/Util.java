package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.*;
import java.util.Properties;

/* Класс Util должен содержать логику настройки соединения с базой данных */
public class Util {
    //  определяем константы
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";

    private final static String URL = "jdbc:mysql://127.0.0.1:3306/testbase";
    private static final String USER = "root";
    private static final String PASS = "root";

    public Util() {
    }

    // HiBerNate============================================
    static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            Properties dbSettings = new Properties();
            dbSettings.put(Environment.URL, URL);
            dbSettings.put(Environment.USER, USER);
            dbSettings.put(Environment.PASS, PASS);
            dbSettings.put(Environment.DRIVER, DB_DRIVER);
            dbSettings.put(Environment.DIALECT, DB_DIALECT);
//            dbSettings.put(Environment.SHOW_SQL, "true");
//            dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            sessionFactory = new Configuration().addProperties(dbSettings)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    // реализуйте настройку соеденения с БД JDBC==========================

    public Connection getUtilConnection() throws SQLException {
        Connection conn;
//        try {
//            Class.forName(DB_DRIVER);
//            conn = DriverManager.getConnection(URL, USER, PASS);
//            if (!conn.isClosed()) {
//                System.out.println("Correct connection to db!  ");
//            }
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
