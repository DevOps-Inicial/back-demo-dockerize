package link.grooverdev.web.api.demo.service;

import link.grooverdev.web.api.demo.entity.User;

import java.util.List;
public interface IUserService {

    List<User> findAll();
    List<User> findAllEnabled(boolean enabled);
    User findByID(String id);
    User save(User newUser);
    User update(String id, User updUser);
    void delete(String id);
}
