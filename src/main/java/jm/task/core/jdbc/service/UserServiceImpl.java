package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.util.List;

/*  здесь создаем объект и получаем реализацию методов из UserDao */
public class UserServiceImpl implements UserService {
//    private final UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
    // Создаем объект DAO чтобы передать в его класс  команды методов
    private final UserDaoHibernateImpl dao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }


}
