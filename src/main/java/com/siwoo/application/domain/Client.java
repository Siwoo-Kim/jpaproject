package com.siwoo.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity @Getter @Setter @ToString(exclude = "orders")
public class Client implements Serializable {

    @Id @GeneratedValue
    @Column(name="client_id")
    private Long id;

    @Embedded
    private Address address;

    private String nickname;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

}
