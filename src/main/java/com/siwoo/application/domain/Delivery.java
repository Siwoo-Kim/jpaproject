package com.siwoo.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @ToString
@Entity
public class Delivery implements Serializable{

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    public enum Status {
        READY, COMPLETE;
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public Delivery() { }

    public Delivery(Address address) {
        this.address = address;
        this.status = Status.READY;
    }

}
