package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.database.DatabaseConnectionPool;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositorySQL implements UserRepository {

    private DatabaseConnectionPool pool;

    public UserRepositorySQL(DatabaseConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            Connection connection = pool.retrieve();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password")));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            Connection connection = pool.retrieve();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password")));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> res = new ArrayList<>();
        try {
            Connection connection = pool.retrieve();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                res.add(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return res;
    }

    @Override
    public User save(User entity) {
        User res = null;
        return res;
    }

    @Override
    public void delete(Long id) {

    }
}
