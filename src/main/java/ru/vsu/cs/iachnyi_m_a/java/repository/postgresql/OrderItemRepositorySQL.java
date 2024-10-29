package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.entity.order.OrderItem;
import ru.vsu.cs.iachnyi_m_a.java.entity.order.OrderItemId;
import ru.vsu.cs.iachnyi_m_a.java.repository.OrderItemRepository;

import java.util.List;
import java.util.Optional;

public class OrderItemRepositorySQL implements OrderItemRepository {
    @Override
    public List<OrderItem> findAllByOrderId(long orderId) {
        return List.of();
    }

    @Override
    public Optional<OrderItem> findById(OrderItemId id) {
        return Optional.empty();
    }

    @Override
    public List<OrderItem> findAll() {
        return List.of();
    }

    @Override
    public OrderItem save(OrderItem entity) {
        return null;
    }

    @Override
    public void delete(OrderItemId id) {

    }
}
