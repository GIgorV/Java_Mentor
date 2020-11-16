package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImpl implements CarDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User findAllByUser_Id(Long userId) {
//        Session session = sessionFactory.getCurrentSession();
//        try {
//            Query<Car> carQuery = session.createQuery("from Car car where car.owner.id = :userId", Car.class)
//                    .setParameter("userId", userId);
//            return carQuery.getResultList();
//        } catch (NoResultException e) {
            return new User();
        }
    }


