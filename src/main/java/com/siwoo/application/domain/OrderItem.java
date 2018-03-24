package com.siwoo.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.omg.PortableInterceptor.ObjectReferenceFactory;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @ToString(exclude = {"item","order"})
@Entity @Table(name = "order_item")
public class OrderItem  implements Serializable {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    //OrderItem can contains the same item with different number of them
    //Total price
    private int orderPrice;
    //Total count for the item
    private int count;

    public void cancel() {
        getItem().updateQuantity(count);
    }

    public int getPrice() {
        return orderPrice * count;
    }

    public OrderItem() { }

    public OrderItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem(item,orderPrice,count);
        //do minus the count from stock
        item.updateQuantity(-count);
        return orderItem;
    }
}
