package ru.gigorv.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gigorv.web.models.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

}
