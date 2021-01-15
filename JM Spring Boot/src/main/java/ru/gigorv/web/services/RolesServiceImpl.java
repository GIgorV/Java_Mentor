package ru.gigorv.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gigorv.web.models.Role;
import ru.gigorv.web.repositories.RolesRepository;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Role getRoleById(Long id) {
        return rolesRepository.findById(id).get();
    }

    @Override
    public List<Role> getRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Role findRoleByRole(String name) {
        return rolesRepository.findRoleByRole(name);
    }
}
