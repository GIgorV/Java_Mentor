package ru.gigorv.web.services;

import ru.gigorv.web.models.Role;

import java.util.List;

public interface RolesService {
    Role getRoleById(Long id);
    List<Role> getRoles();
}
