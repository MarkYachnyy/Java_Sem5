package ru.vsu.cs.iachnyi_m_a.java.service;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.UserRepositoryIMDB;

public class UserService {

    @Getter
    private static UserService instance = new UserService();

    private UserRepository repository;

    private UserService() {
        repository = UserRepositoryIMDB.getInstance();
    }

    public User findUserByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public User findUserById(long id) {
        return repository.findById(id).orElse(null);
    }

    public User registerUser(User user) {
        return repository.save(user);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }
}
