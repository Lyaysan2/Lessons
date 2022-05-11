package ru.itis.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.repositories.DriverRepositoryHibernate;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        DriverRepositoryHibernate driverRepository = new DriverRepositoryHibernate(entityManager);
    }
}
