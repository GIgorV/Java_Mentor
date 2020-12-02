package ru.gigorv.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gigorv.web.models.Role;
import ru.gigorv.web.models.User;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao implements Dao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery(
                "from User u WHERE u.name =:name", User.class).setParameter("name", name).getSingleResult();
    }

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
    public boolean save(User user) {
        entityManager.persist(user);
        return true;
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