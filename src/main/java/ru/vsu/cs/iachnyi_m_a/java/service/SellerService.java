package ru.vsu.cs.iachnyi_m_a.java.service;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.Seller;
import ru.vsu.cs.iachnyi_m_a.java.repository.SellerRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.SellerRepositoryIMDB;

public class SellerService {

    @Getter
    private static SellerService instance = new SellerService();

    private SellerRepository repository;
    private SellerService() {
        repository = new SellerRepositoryIMDB();
    }

    public Seller getSellerById(Long id){
        return repository.findById(id).orElse(null);
    }
}
