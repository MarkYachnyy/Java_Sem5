package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.entity.order.Order;
import ru.vsu.cs.iachnyi_m_a.java.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderRepositorySQL implements OrderRepository {
    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Order save(Order entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
