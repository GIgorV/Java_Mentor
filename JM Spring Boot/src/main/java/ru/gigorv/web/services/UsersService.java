package ru.gigorv.web.services;

import ru.gigorv.web.models.Role;
import ru.gigorv.web.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    User findUserByEmail(String email);
    User findUserById(Long id);
    boolean save(User user);
    boolean deleteUserById(Long id);
    void updateUser(User user);
}
