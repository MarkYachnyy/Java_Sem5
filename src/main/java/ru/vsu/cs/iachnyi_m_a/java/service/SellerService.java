package ru.vsu.cs.iachnyi_m_a.java.service;

import lombok.Getter;

public class SellerService {

    @Getter
    private static SellerService instance = new SellerService();

    private SellerService() {

    }
}
