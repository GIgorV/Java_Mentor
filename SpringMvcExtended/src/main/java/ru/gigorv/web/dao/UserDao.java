package ru.gigorv.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gigorv.web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
public class UserDao implements Dao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;


    @Override
    public void save(User user) {
        executeInsideTransaction(entityManager -> entityManager.persist(user));
    }

    @Override
    public List<User> getAll() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void update(Long id, User user) {
//        user.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
//        user.setEmail(Objects.requireNonNull(params[1], "Email cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(user));
    }

    @Override
    public void delete(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(id));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
