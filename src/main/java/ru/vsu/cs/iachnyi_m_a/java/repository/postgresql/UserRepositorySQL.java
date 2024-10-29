package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserRepositorySQL implements UserRepository {
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
