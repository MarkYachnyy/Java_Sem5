package ru.vsu.cs.iachnyi_m_a.java.context;

import ru.vsu.cs.iachnyi_m_a.java.fake_db.InMemoryDatabase;
import ru.vsu.cs.iachnyi_m_a.java.repository.CartRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.ProductRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.SellerRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.UserRepository;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.CartRepositoryIMDB;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.ProductRepositoryIMDB;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.SellerRepositoryIMDB;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.UserRepositoryIMDB;
import ru.vsu.cs.iachnyi_m_a.java.service.CartService;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;
import ru.vsu.cs.iachnyi_m_a.java.service.SellerService;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.util.*;

public class MyApplicationContext implements ApplicationContext{
    private List<Object> beans;

    public MyApplicationContext() {
        beans = new ArrayList<>();
        beans.add(new CartRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new ProductRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new UserRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new SellerRepositoryIMDB(InMemoryDatabase.getInstance()));

        beans.add(new UserService(this.getBean(UserRepository.class)));
        beans.add(new ProductService(this.getBean(ProductRepository.class)));
        beans.add(new SellerService(this.getBean(SellerRepository.class)));
        beans.add(new CartService(this.getBean(CartRepository.class)));
    }

    public <T> T getBean(Class<T> clazz){
        return beans.stream().filter(clazz::isInstance).map(clazz::cast).findFirst().orElse(null);
    }
}
