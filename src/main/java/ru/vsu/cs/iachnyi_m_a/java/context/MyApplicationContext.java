package ru.vsu.cs.iachnyi_m_a.java.context;

import ru.vsu.cs.iachnyi_m_a.java.fake_db.InMemoryDatabase;
import ru.vsu.cs.iachnyi_m_a.java.repository.*;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.*;
import ru.vsu.cs.iachnyi_m_a.java.service.*;

import java.util.*;

public class MyApplicationContext implements ApplicationContext{
    private final List<Object> beans;

    public MyApplicationContext() {
        beans = new ArrayList<>();

        beans.add(new OrderRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new OrderItemRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new CartRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new ProductRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new UserRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new SellerRepositoryIMDB(InMemoryDatabase.getInstance()));

        beans.add(new OrderService(this.getBean(OrderRepository.class), this.getBean(OrderItemRepository.class)));
        beans.add(new UserService(this.getBean(UserRepository.class)));
        beans.add(new ProductService(this.getBean(ProductRepository.class)));
        beans.add(new SellerService(this.getBean(SellerRepository.class)));
        beans.add(new CartService(this.getBean(CartRepository.class)));
    }

    public <T> T getBean(Class<T> clazz){
        return beans.stream().filter(clazz::isInstance).map(clazz::cast).findFirst().orElse(null);
    }
}
