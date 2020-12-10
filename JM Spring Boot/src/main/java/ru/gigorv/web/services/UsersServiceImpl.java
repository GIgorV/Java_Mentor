package ru.gigorv.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gigorv.web.models.Role;
import ru.gigorv.web.models.User;
import ru.gigorv.web.repositories.RolesRepository;
import ru.gigorv.web.repositories.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        Optional<User> userFromDb = usersRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Override
    public boolean save(User user) {
        User userFromDB = usersRepository.findByName(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
