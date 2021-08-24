package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;

import org.hibernate.*;
import org.hibernate.SessionFactory;


import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    // Создаем объект sessionFactory
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        // открываем сессию
//        Session session = sessionFactory.openSession();
        try (Session session = Util.getSessionFactory().openSession()) {
            // начинаем транзакцию
            session.beginTransaction();
            System.out.println("Ok");
            // создаем запросы к базе в SQL
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(45) NOT NULL," +
                    "lastname VARCHAR(45) NOT NULL, " +
                    "age TINYINT(3) NOT NULL, " +
                    "PRIMARY KEY (id));")
                    .executeUpdate();

            // потверждаем изменения
            System.out.println("Session in transaction");
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }
//    public void createUsersTable() {
//        try (Session session = Util.getSessionFactory().openSession()) {
//            session.beginTransaction();
//            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT(255) NOT NULL AUTO_INCREMENT, " +
//                    "name CHAR(60), lastName CHAR(60), age INT NOT NULL, PRIMARY KEY (id));")
//                    .executeUpdate();
//            session.getTransaction().commit();
//        }
//    }

    @Override
    public void dropUsersTable() {
//        Session session = sessionFactory.openSession();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
//            session.createSQLQuery("DROP TABLE IF EXISTS users;");

            session.createSQLQuery("DROP TABLE IF EXISTS users;")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public Object saveUser(String name, String lastName, byte age) {
//        Session session = sessionFactory.openSession();
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
//        Session session = sessionFactory.openSession();
        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
            users = session.createCriteria(User.class).list();


//            SQLQuery query = session.createSQLQuery("SELECT * FROM users;");
//            query.addEntity(User.class);
//            users = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
        }
//        assert users != null;
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
//        Session session = sessionFactory.openSession();
        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
            session.createQuery("DELETE FROM User ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }
}
