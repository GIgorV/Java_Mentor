package ru.gigorv.web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gigorv.web.models.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDao implements Dao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        TypedQuery<User> typedQuery = entityManager.createQuery("from User", User.class);
        return typedQuery.getResultList();
    }

    @Override
//    public Optional<User> get(Long id) {
    public User get(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(Long id, User transientUser) {
        entityManager.merge(transientUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(get(id));
    }
}