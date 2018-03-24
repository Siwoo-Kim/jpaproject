package com.siwoo.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter @Setter @ToString(exclude = "categories")
@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item implements Serializable {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public static class NotEnoughQuantityException extends RuntimeException {
        public NotEnoughQuantityException(String message) {
            super(message);
        }
    }

    public void updateQuantity(int quantity) {
        if(quantity == 0) {
            return;
        } else if(quantity < 0) {
            log.warn(quantity+"");
            int restQuantity = stockQuantity + quantity;
            log.warn(":"+restQuantity+"");
            if(restQuantity < 0) {
                throw new NotEnoughQuantityException("Item[stockQuantity:" + this.stockQuantity + "] is not enough for request ");
            } else {
                this.stockQuantity = restQuantity;
                return;
            }
        }
        this.stockQuantity += quantity;
    }

}
