package ru.gigorv.web.service;


import ru.gigorv.web.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    Optional<User> get(Long id);
    User get(Long id);
    List<User> getAll();
    void save(User user);
    void update(Long id, User user);
    void delete(Long id);
}
