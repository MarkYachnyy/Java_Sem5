package ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.fake_db.InMemoryDatabase;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserRepositoryIMDB implements UserRepository {

    @Getter
    private static UserRepositoryIMDB instance = new UserRepositoryIMDB();

    private InMemoryDatabase database = InMemoryDatabase.getInstance();

    private UserRepositoryIMDB(){}

    @Override
    public Optional<User> findById(Long id) {
        return database.getAllUsers().stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public List<User> findAll() {
        return database.getAllUsers();
    }

    @Override
    public User save(User entity) {
        List<User> allUsers = database.getAllUsers();
        if (allUsers.stream().anyMatch(user -> user.getId() == entity.getId())) {
            return database.updateUser(entity);
        } else {
            return database.insertUser(entity);
        }
    }

    @Override
    public void delete(Long id) {}

    @Override
    public Optional<User> findByEmail(String email) {
        return database.getAllUsers().stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }
}
