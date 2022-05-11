package ru.itis.repositories;

import ru.itis.models.Driver;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DriverRepositoryHibernate implements CrudRepository<Driver, Long> {

    private final EntityManager entityManager;

    public DriverRepositoryHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return Optional.of(entityManager.find(Driver.class, id));
    }

    @Override
    public List<Driver> findAll() {
        TypedQuery<Driver> query = entityManager.createQuery("select drivers from Driver drivers", Driver.class);
        return query.getResultList();

    }

    @Override
    public void save(Driver driver) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(driver);
        transaction.commit();

    }

    @Override
    public void delete(Long id) {
        Driver driver = findById(id).orElseThrow(IllegalArgumentException::new);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(driver);
        transaction.commit();
    }
}
