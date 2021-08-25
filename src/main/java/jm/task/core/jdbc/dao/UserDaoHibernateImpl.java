package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.*;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        // открываем сессию
        try (Session session = Util.getSessionFactory().openSession()) {
            // начинаем транзакцию
            session.beginTransaction();
//            System.out.println("Ok");
            // создаем запросы к базе в SQL
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(45) NOT NULL," +
                    "lastname VARCHAR(45) NOT NULL, " +
                    "age TINYINT(3) NOT NULL, " +
                    "PRIMARY KEY (id));")
                    .executeUpdate();

            // потверждаем изменения
//            System.out.println("Session in transaction");
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
//            System.out.println("Ok");
            // запросы к базе
            session.createSQLQuery("DROP TABLE IF EXISTS users;")
                    //если  SQLQuery то executeUpdate нужен
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Override
    public Object saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
//            System.out.println("Ok");
            // запросы к базе
            // пользуемся методом Hibernate from SharedSessionContract.class
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
//            System.out.println("Ok");
            // запросы к базе
            // пользуемся методом Hibernate from SharedSessionContract.class
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List getAllUsers() {
        List users = null;
        /* Session session = sessionFactory.openSession(); */
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
//            System.out.println("Ok");
            // запросы к базе
            // перечеркнут потому, что устаревший
            users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
        } catch (Exception ignored) {
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
//            System.out.println("Ok");
            // запросы к базе
            // пользуемся методом Hibernate from SharedSessionContract.class
            session.createQuery("DELETE FROM User ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ignored) {
        }
    }
}
