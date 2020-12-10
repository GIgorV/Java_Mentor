package ru.gigorv.web.services;

import ru.gigorv.web.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    User findUserByName(String name);
    User findUserById(Long id);
    void save(User user);
    void deleteUserById(Long id);
}
