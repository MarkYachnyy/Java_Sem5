package ru.vsu.cs.iachnyi_m_a.java.service;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.UserRepositoryIMDB;

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
            repository.save(user);
            return true;
        }
    }

    public User updateUser(User user) {
        return repository.save(user);
    }
}
