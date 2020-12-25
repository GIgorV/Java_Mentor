package ru.gigorv.web.dao;

import ru.gigorv.web.models.User;
import java.util.List;
import java.util.Optional;

public interface Dao {
//   Optional<User> get(Long id);
   User get(Long id);
   List<User> getAll();
   boolean save(User user);
   void update(Long id, User user);
   void delete(Long id);
   User getUserByName(String name);
}
