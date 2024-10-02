package ru.vsu.cs.iachnyi_m_a.java.repository;

import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItem;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItemId;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends DataRepository<CartItem, CartItemId> {
    List<CartItem> findAllByUserId(long userId);
    Optional<CartItem> findByUserIdAndProductId(long userId, long productId);
}
