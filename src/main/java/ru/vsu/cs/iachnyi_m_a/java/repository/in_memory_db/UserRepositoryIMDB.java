package ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.fake_db.InMemoryDatabase;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserRepositoryIMDB implements UserRepository {

    private InMemoryDatabase database;

    public UserRepositoryIMDB(InMemoryDatabase database){
        this.database = database;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> res = database.getAllUsers().stream().filter(user -> user.getId() == id).findFirst();
        System.err.println("found user by id: " + res);
        return res;
    }

    @Override
    public List<User> findAll() {
        List<User> res = database.getAllUsers();
        System.err.println("found users: " + res);
        return res;
    }

    @Override
    public User save(User entity) {
        List<User> allUsers = database.getAllUsers();
        if (allUsers.stream().anyMatch(user -> user.getId() == entity.getId())) {
            System.err.println("updating user: " + entity);
            return database.updateUser(entity);
        } else {
            System.err.println("creating user: " + entity);
            return database.insertUser(entity);
        }
    }

    @Override
    public void delete(Long id) {}

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> res = database.getAllUsers().stream().filter(user -> user.getEmail().equals(email)).findFirst();
        System.err.println("found user by email: " + res);
        return res;
    }
}
