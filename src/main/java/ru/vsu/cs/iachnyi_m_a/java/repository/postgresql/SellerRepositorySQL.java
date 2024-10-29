package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.entity.Seller;
import ru.vsu.cs.iachnyi_m_a.java.repository.SellerRepository;

import java.util.List;
import java.util.Optional;

public class SellerRepositorySQL implements SellerRepository {
    @Override
    public Optional<Seller> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

    @Override
    public Seller save(Seller entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
