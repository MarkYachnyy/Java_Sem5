package ru.vsu.cs.iachnyi_m_a.java.service;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.repository.ProductRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.ProductRepositoryIMDB;

import java.util.List;

public class ProductService {

    @Getter
    private static ProductService instance = new ProductService();

    private ProductService(){}

    private ProductRepository repository = ProductRepositoryIMDB.getInstance();

    public Product getProductById(long id){
        return repository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public List<Product> getAllProductsOfSeller(long id){
        return repository.findAllBySellerId(id);
    }

    public Product updateProduct(Product product){
        return repository.save(product);
    }

}
