package ru.vsu.cs.iachnyi_m_a.java.fake_db;

import lombok.Getter;
import ru.vsu.cs.iachnyi_m_a.java.entity.Product;
import ru.vsu.cs.iachnyi_m_a.java.entity.Seller;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.entity.Warehouse;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItem;
import ru.vsu.cs.iachnyi_m_a.java.entity.cart.CartItemId;
import ru.vsu.cs.iachnyi_m_a.java.entity.order.Order;
import ru.vsu.cs.iachnyi_m_a.java.entity.order.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InMemoryDatabase {

    @Getter
    private static final InMemoryDatabase instance = new InMemoryDatabase();

    private InMemoryDatabase() {
        this.sellers = new ArrayList<>();
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        this.warehouses = new ArrayList<>();
        this.cartItems = new ArrayList<>();
        this.orderItems = new ArrayList<>();

        Random random = new Random();
        for (int i = 1; i <= 2; i++) {
            insertUser(new User(0, "user" + i, "email" + i + "@gmail.com", "password" + i));
        }
        insertUser(new User(0, "m", "m", "m"));
        for (int i = 1; i <= 5; i++) {
            insertSeller(new Seller(0, "Продавец" + i));
        }
        for (int i = 1; i <= 2; i++) {
            insertWarehouse(new Warehouse(0, "Склад" + i));
        }
        for (Seller seller : sellers) {
            for (int i = 1; i <= 5; i++) {
                insertProduct(new Product(0, seller.getId(), warehouses.get(random.nextInt(warehouses.size())).getId(), "Товар" + i + "Продавца" + seller.getId(), i * 100, 5));
            }
        }
    }

    private final List<User> users;
    private long nextUserId = 1;

    public User insertUser(User user) {
        if (users.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new RuntimeException("User with such email already exists");
        }
        User toInsert = new User(nextUserId, user.getName(), user.getEmail(), user.getPassword());
        users.add(toInsert);
        nextUserId++;
        return new User(toInsert);
    }

    public User updateUser(User user) {
        User toUpdate = users.stream().filter(user1 -> user1.getId() == user.getId()).findFirst().orElse(null);
        if (toUpdate != null) {
            toUpdate.setName(user.getName());
            toUpdate.setPassword(user.getPassword());
            toUpdate.setEmail(user.getEmail());
            return new User(toUpdate);
        } else return null;
    }

    public List<User> getAllUsers() {
        return List.copyOf(users);
    }

    private final List<Product> products;
    private long nextProductId = 1;

    public Product insertProduct(Product product) {
        Product toInsert = new Product(nextProductId, product.getSellerId(), product.getWarehouseId(),
                product.getName(), product.getPrice(), product.getStockQuantity());
        products.add(toInsert);
        nextProductId++;
        return new Product(toInsert);
    }

    public Product updateProduct(Product product) {
        Product toUpdate = products.stream().filter(product1 -> product1.getId() == product.getId()).findFirst().orElse(null);
        if (toUpdate != null) {
            toUpdate.setSellerId(product.getSellerId());
            toUpdate.setWarehouseId(product.getWarehouseId());
            toUpdate.setName(product.getName());
            toUpdate.setPrice(product.getPrice());
            toUpdate.setStockQuantity(product.getStockQuantity());
            return new Product(toUpdate);
        } else return null;
    }

    public List<Product> getAllProducts() {
        return List.copyOf(products);
    }

    private final List<Seller> sellers;
    private long nextSellerId = 1;

    public Seller insertSeller(Seller seller) {
        if (sellers.stream().anyMatch(s -> s.getName().equals(seller.getName()))) {
            throw new RuntimeException("Seller with such name already exists");
        }
        Seller toInsert = new Seller(nextSellerId, seller.getName());
        sellers.add(toInsert);
        nextSellerId++;
        return new Seller(toInsert);
    }

    public List<Seller> getAllSellers() {
        return List.copyOf(sellers);
    }

    private final List<Warehouse> warehouses;
    private long nextWarehouseId = 1;

    public Warehouse insertWarehouse(Warehouse warehouse) {
        Warehouse toInsert = new Warehouse(nextWarehouseId, warehouse.getName());
        warehouses.add(toInsert);
        nextWarehouseId++;
        return new Warehouse(toInsert);
    }

    private final List<Order> orders = new ArrayList<>();
    private long nextOrderId = 1;

    public Order insertOrder(Order order) {
        Order toInsert = new Order(nextOrderId, order.getUserId(), order.getDate(), order.getStatus(), order.getItems());
        orders.add(toInsert);
        nextOrderId++;
        return new Order(toInsert);
    }

    public List<Order> getAllOrders(){
        return List.copyOf(orders);
    }

    private final List<OrderItem> orderItems;

    public OrderItem insertOrderItem(OrderItem orderItem) {
        if (orderItems.stream().anyMatch(oi -> oi.getId().equals(orderItem.getId()))) {
            throw new RuntimeException("OrderItem with such id already exists");
        }
        OrderItem toInsert = new OrderItem(orderItem);
        orderItems.add(toInsert);
        return new OrderItem(toInsert);
    }

    public List<OrderItem> getAllOrderItems(){
        return List.copyOf(orderItems);
    }

    private final List<CartItem> cartItems;

    public CartItem insertCartItem(CartItem cartItem) {
        if (cartItems.stream().anyMatch(ci -> ci.getId().equals(cartItem.getId()))) {
            throw new RuntimeException("CartItem with such id already exists");
        }
        CartItem toInsert = new CartItem(cartItem);
        cartItems.add(toInsert);
        return new CartItem(toInsert);
    }

    public CartItem updateCartItem(CartItem cartItem) {
        CartItem toUpdate = cartItems.stream().filter(oi -> oi.getId().equals(cartItem.getId())).findFirst().orElse(null);
        if (toUpdate != null) {
            toUpdate.setQuantity(cartItem.getQuantity());
            return new CartItem(toUpdate);
        } else return null;
    }

    public List<CartItem> getAllCartItems() {
        return List.copyOf(this.cartItems);
    }

    public void deleteCartItem(CartItemId id) {
        cartItems.removeIf(ci -> ci.getId().equals(id));
    }

}
