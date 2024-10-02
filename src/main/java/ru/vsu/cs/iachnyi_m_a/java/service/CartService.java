package ru.vsu.cs.iachnyi_m_a.java.service;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.repository.CartRepository;

public class CartService {

    @Getter
    private static CartService instance = new CartService();

    private CartRepository cartRepository;

    private CartService() {}
}
