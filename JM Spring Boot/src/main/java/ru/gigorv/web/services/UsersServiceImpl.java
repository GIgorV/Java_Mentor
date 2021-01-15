package ru.gigorv.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gigorv.web.models.Role;
import ru.gigorv.web.models.User;
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
    public User findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userFromDb = usersRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Override
    public boolean save(User user) {
        User userFromDB = usersRepository.findByEmail(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
//        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
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

    @Override
    public void updateUser(User user) {
//        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        usersRepository.save(user);
    }
}
