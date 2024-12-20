package ru.vsu.cs.iachnyi_m_a.java.service;

import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;
import ru.vsu.cs.iachnyi_m_a.java.security.PasswordEncoder;

import java.util.Optional;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findUserByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public User findUserById(long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean registerUser(User user) {
        Optional<User> userOptional = repository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return false;
        } else {
            user.setPassword(PasswordEncoder.encode(user.getPassword()));
            repository.save(user);
            return true;
        }
    }

    public User updateUser(User user) {
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
