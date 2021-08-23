package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    //    Session session = sessionFactory.openSession();
    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(45) NOT NULL," +
                    "lastname VARCHAR(45) NOT NULL, " +
                    "age TINYINT(3) NOT NULL, " +
                    "PRIMARY KEY (id));");
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
//            session.createSQLQuery("DROP TABLE IF EXISTS users;");
            session.createSQLQuery("DROP TABLE IF EXISTS users;")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public Object saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        User user = new User();
        try {
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return null;
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        try {
            session.beginTransaction();
            System.out.println("Ok");
            // запросы к базе
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
//       List arrayList = null;
//        Session session = sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//            System.out.println("Ok");
//            // запросы к базе
//            User users = (User)session.createCriteria(User.class).list();
//            arrayList = session.createSQLQuery("SELECT * FROM users;").list();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//            sessionFactory.close();
//        }
////        List<User> users = session.create
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }

}
