package ru.vsu.cs.iachnyi_m_a.java.entity.order;

import lombok.Data;

@Data
public class OrderItem {
    private OrderItemId id;
    private int amount;
    private int price;

    public OrderItem(OrderItem orderItem1){
        this.id = new OrderItemId(orderItem1.getId());
        this.amount = orderItem1.getAmount();
        this.price = orderItem1.getPrice();
    }
}
