package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItem;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItemId;
import ru.vsu.cs.iachnyi_m_a.java.repository.CartRepository;

import java.util.List;
import java.util.Optional;

public class CartRepositorySQL implements CartRepository {
    @Override
    public List<CartItem> findAllByUserId(long userId) {
        return List.of();
    }

    @Override
    public Optional<CartItem> findById(CartItemId id) {
        return Optional.empty();
    }

    @Override
    public List<CartItem> findAll() {
        return List.of();
    }

    @Override
    public CartItem save(CartItem entity) {
        return null;
    }

    @Override
    public void delete(CartItemId id) {

    }
}
