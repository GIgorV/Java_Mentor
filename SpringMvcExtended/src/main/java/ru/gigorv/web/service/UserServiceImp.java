package ru.gigorv.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gigorv.web.dao.Dao;
import ru.gigorv.web.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private Dao userDao;
    @Transactional
    @Override
    public Optional<User> get(Long id) {
        return userDao.get(id);
    }
    @Transactional
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }
    @Transactional
    @Override
    public void update(Long id, User user) {
        userDao.update(id, user);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
