package ru.vsu.cs.iachnyi_m_a.java.entity.cart;

import lombok.Data;

@Data
public class CartItemId {
    private long userId;
    private long productId;

    public CartItemId(CartItemId cartItemId1) {
        this.userId = cartItemId1.userId;
        this.productId = cartItemId1.productId;
    }
}
