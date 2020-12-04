package ru.gigorv.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gigorv.web.models.Role;


public interface RolesRepository extends JpaRepository<Role, Long> {

//    Role findByName(String role_user);
}
