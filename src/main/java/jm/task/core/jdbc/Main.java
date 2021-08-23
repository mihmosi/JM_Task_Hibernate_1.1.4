package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

/*

 * Классы dao/service должны реализовывать соответствующие интерфейсы
 * Класс dao должен иметь конструктор пустой/по умолчанию
 * Все поля должны быть private
 * service переиспользует методы dao
 * Обработка всех исключений, связанных с работой с базой данных должна находиться в dao
 * Класс Util должен содержать логику настройки соединения с базой данных
 * <p>
 * <p>
 * Необходимые операции:
 * <p>
 * Создание таблицы для User(ов) – не должно приводить к исключению, если такая
 * таблица уже существует
 * Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
 * Очистка содержания таблицы
 * Добавление User в таблицу
 * Удаление User из таблицы ( по id )
 * Получение всех User(ов) из таблицы
 * <p>
 * <p>
 * Алгоритм работы приложения:
 * <p>
 * В методе main класса Main должны происходить следующие операции:
 * <p>
 * Создание таблицы User(ов)
 * Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления
 * должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
 * Получение всех User из базы и вывод в консоль ( должен быть переопределен
 * toString в классе User)
 * Очистка таблицы User(ов)
 * Удаление таблицы
  UserHibernateDaoImpl должен реализовывать интерефейс UserDao
 В класс Util должна быть добавлена конфигурация для Hibernate
  ( рядом с JDBC), без использования xml.
 Service на этот раз использует реализацию dao через Hibernate
 Методы создания и удаления таблицы пользователей в классе
 UserHibernateDaoImpl должны быть реализованы с помощью SQL.
 */
public class Main {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = Util.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//            // запросы к базе
//
//            System.out.println("Ok");
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//            sessionFactory.close();
//        }

//    }

//        try  {
//            // Check MySQL db version
//            String sql = "select version()";
//
//            String result = (String) session.createNativeQuery(sql).getSingleResult();
//            System.out.println("MySQL db Version is:::");
//            System.out.println(result);
//            session.close();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();
////создание БД
        userService.createUsersTable();
//        /*===========================createUsersTable=======================================================*/
        /* Добавление 4 User(ов) в таблицу с данными на свой выбор. */
        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < 4; i++) {
                System.out.println("Input name: ");
                String name = scanner.next();

                System.out.println("Input lastname: ");
                String lastname = scanner.next();

                System.out.println("Input age: ");
                byte age = scanner.nextByte();

//создание юзеров
                userService.saveUser(name, lastname, age);
                /*===============================saveUser=======================================*/
                System.out.printf("User с именем – %s добавлен в базу данных users\n", name);
            }
            userService.getAllUsers();
        }
        //получение всех юзеров
        System.out.println(userService.getAllUsers());

        /*========================getAllUsers=======================================*/
        // очистка талицы
        userService.cleanUsersTable();
        /*================================================================*/

        // удаление таблицы
        userService.dropUsersTable();
        /*===========================dropUsersTable=================================*/
    }
}