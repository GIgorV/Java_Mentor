package ru.gigorv.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gigorv.web.models.User;
import ru.gigorv.web.repositories.UsersRepository;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User findUserByName(String name) {
        return usersRepository.findByName(name);
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void deleteUserById(Long id) {

    }
}
