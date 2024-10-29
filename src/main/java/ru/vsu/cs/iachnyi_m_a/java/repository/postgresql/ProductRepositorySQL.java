package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductRepositorySQL implements ProductRepository {
    @Override
    public List<Product> findAllBySellerId(Long sellerId) {
        return List.of();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product save(Product entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
