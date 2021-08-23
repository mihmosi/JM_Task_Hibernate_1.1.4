package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* здесь реализуем методы */
public class UserDaoJDBCImpl implements UserDao {
    /* Обработка всех исключений, связанных с работой с базой данных должна находиться в dao*/

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connect = new Util().getUtilConnection()) {
            Statement statement = connect.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(45) NOT NULL," +
                    "lastname VARCHAR(45) NOT NULL, " +
                    "age TINYINT(3) NOT NULL, " +
                    "PRIMARY KEY (id));");
        } catch (SQLException throwables) {
            throwables.getStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Connection connection = new Util().getUtilConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException throwables) {
            throwables.getStackTrace();
        }
    }

    public Object saveUser(String name, String lastName, byte age) {
        try (Connection connection = new Util().getUtilConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (name, lastname, age) VALUES  (?, ?, ?);");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.getStackTrace();
        }
        return null;
    }


    public void removeUserById(long id) {
        try (Connection connection = new Util().getUtilConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
            preparedStatement.setLong(1, id);
        } catch (SQLException throwables) {
            throwables.getStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> arrayUsers = new ArrayList<>();
        try (Connection connection = new Util().getUtilConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users;")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                Byte age = resultSet.getByte("age");

                arrayUsers.add(new User(name, lastname, age));
            }
        } catch (SQLException throwables) {
            throwables.getStackTrace();
        }

        return arrayUsers;
    }

    public void cleanUsersTable() {
        try (Connection connection = new Util().getUtilConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE users")) {
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.getStackTrace();
        }
    }
}


