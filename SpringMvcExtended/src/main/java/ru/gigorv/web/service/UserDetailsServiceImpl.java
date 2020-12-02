package ru.gigorv.web.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gigorv.web.dao.Dao;
import ru.gigorv.web.models.User;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {

    private final Dao dao;

    public UserDetailsServiceImpl(Dao dao) {
        this.dao = dao;
    }
    // «Пользователь» – это просто Object. В большинстве случаев он может быть приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = dao.getUserByName(name);
        if (user == null){
            throw new UsernameNotFoundException(String.format("User '%s' not found", name));
        }
        return user;
    }

    @Override
//    public Optional<User> get(Long id) {
    public User get(Long id) {
        return dao.get(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean save(User user) {
        dao.save(user);
        return true;
    }

    @Override
    public void update(Long id, User user) {
        dao.update(id, user);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }
}