package jm.task.core.jdbc.util;

import java.sql.*;

/* Класс Util должен содержать логику настройки соединения с базой данных */
public class Util {
    //  определяем константы
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/testbase";
    private final static String URLFIXED =
            "jdbc:mysql://127.0.0.1:3306/testbase?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static Connection con;
    public Util(){   }
    // реализуйте настройку соеденения с БД

    private static void loadMysqlDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getUtilConnection() {

        try {
            loadMysqlDriver();
            Connection conn = DriverManager.getConnection(URLFIXED, LOGIN, PASSWORD);
            if (!conn.isClosed()) {
                System.out.println("Correct connection to db! ");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con;
    }

}
