package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<User> findUserByCar(String model, int series) {
        try {
            Query<User> query = sessionFactory.getCurrentSession().createQuery
                    ("from User user where user.car.model= :model and user.car.series= :series", User.class)
                    .setParameter("model", model)
                    .setParameter("series", series);
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
