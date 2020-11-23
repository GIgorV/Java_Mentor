package ru.gigorv.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gigorv.web.dao.Dao;
import ru.gigorv.web.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private Dao userDao;

    @Override
//    public Optional<User> get(Long id) {
    public User get(Long id) {
        return userDao.get(id);
    }
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(Long id, User user) {
        userDao.update(id, user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
