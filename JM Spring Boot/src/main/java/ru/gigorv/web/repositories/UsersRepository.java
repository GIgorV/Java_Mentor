package ru.gigorv.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gigorv.web.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
