package com.siwoo.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString(exclude = {"client","orderItems","delivery"})
@Entity(name="Orders") @Table(name = "orders") //name is changed o avoid exception in database
public class Order  implements Serializable {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDate orderDate;

    public enum Status {
        CANCEL, ORDER;
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public void setClient(Client client) {
        this.client = client;
        if(!client.getOrders().contains(this)) {
            client.getOrders().add(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        if(orderItem.getOrder() != this) {
            orderItem.setOrder(this);
        }
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        if(delivery.getOrder() != this) {
            delivery.setOrder(this);
        }
    }

    public static Order createOrder(Client client, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setClient(client);
        order.setDelivery(delivery);
        for(OrderItem orderItem: orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(Status.ORDER);
        order.setOrderDate(LocalDate.now());
        return order;
    }
    public static class AlreadySentOrderException extends RuntimeException{
        public AlreadySentOrderException(String message) {
            super(message);
        }
    }
    public void cancel() {
        if(delivery.getStatus() == Delivery.Status.COMPLETE) {
            throw new AlreadySentOrderException("Order[id"+ id + "] cannot cancel since the order already sent to customer.");
        }
        this.setStatus(Status.CANCEL);
        for(OrderItem orderItem: orderItems) {
            orderItem.cancel();
        }
    }
    public int getPrice() {
        int price = 0;
        for(OrderItem orderItem: orderItems) {
            price += orderItem.getPrice();
        }
        return price;
    }
}
