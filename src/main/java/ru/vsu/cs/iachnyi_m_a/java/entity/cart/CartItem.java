package ru.vsu.cs.iachnyi_m_a.java.entity.cart;

import lombok.Data;

@Data
public class CartItem {
    CartItemId id;
    int quantity;

    public CartItem(CartItem cartItem1) {
        id = new CartItemId(cartItem1.getId());
        quantity = cartItem1.getQuantity();
    }
}
