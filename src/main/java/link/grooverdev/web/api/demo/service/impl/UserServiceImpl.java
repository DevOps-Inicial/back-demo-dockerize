package link.grooverdev.web.api.demo.service.impl;

import link.grooverdev.web.api.demo.entity.User;
import link.grooverdev.web.api.demo.exception.ResourceNotFoundException;
import link.grooverdev.web.api.demo.repository.UserRepository;
import link.grooverdev.web.api.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllEnabled(boolean enabled) {
        return userRepository.findAllByEnabled(enabled);
    }

    @Override
    public User findByID(String id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User update(String id, User updUser) {

        var updatedUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));

        if (updatedUser != null) {
            updatedUser.setUsername(updUser.getUsername());
            updatedUser.setEmail(updUser.getEmail());
            updatedUser.setPassword(updUser.getPassword());
            updatedUser.setEnabled(updUser.isEnabled());
        }
        assert updatedUser != null;
        return userRepository.save(updatedUser);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
