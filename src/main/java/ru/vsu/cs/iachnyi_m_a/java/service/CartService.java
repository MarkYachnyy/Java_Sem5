package ru.vsu.cs.iachnyi_m_a.java.service;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItem;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItemId;
import ru.vsu.cs.iachnyi_m_a.java.repository.CartRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.CartRepositoryIMDB;

import java.util.List;

public class CartService {

    @Getter
    private static CartService instance = new CartService();

    private CartRepository cartRepository = CartRepositoryIMDB.getInstance();

    private CartService() {}

    public List<CartItem> getCartOfUser(Long userId){
        return cartRepository.findAllByUserId(userId);
    }

    public void deleteCartItem(CartItemId cartItemId){
        cartRepository.delete(cartItemId);
    }

    public CartItem saveCartItem(CartItem cartItem){
        return cartRepository.save(cartItem);
    }
}
