package ru.vsu.cs.iachnyi_m_a.java.context;

import ru.vsu.cs.iachnyi_m_a.java.database.DatabaseConnectionPool;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.InMemoryDatabase;
import ru.vsu.cs.iachnyi_m_a.java.repository.*;
import ru.vsu.cs.iachnyi_m_a.java.repository.in_memory_db.*;
import ru.vsu.cs.iachnyi_m_a.java.repository.postgresql.PickupPointRepositorySQL;
import ru.vsu.cs.iachnyi_m_a.java.repository.postgresql.ProductRepositorySQL;
import ru.vsu.cs.iachnyi_m_a.java.repository.postgresql.SellerRepositorySQL;
import ru.vsu.cs.iachnyi_m_a.java.repository.postgresql.UserRepositorySQL;
import ru.vsu.cs.iachnyi_m_a.java.service.*;

import java.util.*;

public class MyApplicationContext implements ApplicationContext{
    private final List<Object> beans;

    public MyApplicationContext() {
        beans = new ArrayList<>();

        beans.add(new DatabaseConnectionPool("jdbc:postgresql://localhost:5432/java_task", "postgres", "1234", "org.postgresql.Driver", 1));
        beans.add(new OrderRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new OrderItemRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new CartRepositoryIMDB(InMemoryDatabase.getInstance()));
        beans.add(new ProductRepositorySQL(getBean(DatabaseConnectionPool.class)));
        beans.add(new UserRepositorySQL(getBean(DatabaseConnectionPool.class)));
        beans.add(new SellerRepositorySQL(getBean(DatabaseConnectionPool.class)));
        beans.add(new PickupPointRepositorySQL(getBean(DatabaseConnectionPool.class)));

        beans.add(new OrderService(this.getBean(OrderRepository.class), this.getBean(OrderItemRepository.class)));
        beans.add(new UserService(this.getBean(UserRepository.class)));
        beans.add(new ProductService(this.getBean(ProductRepository.class)));
        beans.add(new SellerService(this.getBean(SellerRepository.class)));
        beans.add(new CartService(this.getBean(CartRepository.class)));
        beans.add(new PickupPointService(this.getBean(PickupPointRepository.class)));
    }

    public <T> T getBean(Class<T> clazz){
        return beans.stream().filter(clazz::isInstance).map(clazz::cast).findFirst().orElse(null);
    }
}
