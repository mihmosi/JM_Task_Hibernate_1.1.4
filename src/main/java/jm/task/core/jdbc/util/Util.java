package jm.task.core.jdbc.util;

import java.sql.*;

/* Класс Util должен содержать логику настройки соединения с базой данных */
public class Util {
    //  определяем константы
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL =
            "jdbc:mysql://127.0.0.1:3306/testbase?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public Util() {
    }
    // реализуйте настройку соеденения с БД

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
