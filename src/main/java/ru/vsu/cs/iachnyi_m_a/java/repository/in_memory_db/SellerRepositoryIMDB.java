package ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db;

import ru.vsu.cs.iachnyi_m_a.java.entity.Seller;
import ru.vsu.cs.iachnyi_m_a.java.fake_db.InMemoryDatabase;
import ru.vsu.cs.iachnyi_m_a.java.repository.SellerRepository;

import java.util.List;
import java.util.Optional;

public class SellerRepositoryIMDB implements SellerRepository {

    public SellerRepositoryIMDB(){
        database = InMemoryDatabase.getInstance();
    }

    private InMemoryDatabase database = InMemoryDatabase.getInstance();

    @Override
    public Optional<Seller> findById(Long id) {
        return database.getAllSellers().stream().filter(seller -> seller.getId() == id).findFirst();
    }

    @Override
    public List<Seller> findAll() {
        return database.getAllSellers();
    }

    @Override
    public Seller save(Seller entity) {
        if(database.getAllSellers().stream().anyMatch(seller -> seller.getId() == entity.getId())) {
            return database.insertSeller(entity);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {

    }
}
