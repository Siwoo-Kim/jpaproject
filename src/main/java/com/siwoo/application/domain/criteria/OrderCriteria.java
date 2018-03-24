package com.siwoo.application.domain.criteria;

import com.siwoo.application.domain.Order;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class OrderCriteria {

    private String clientName;
    private Order.Status status;

    protected OrderCriteria() { }

    public OrderCriteria(String clientName, Order.Status status) {
        this.clientName = clientName;
        this.status = status;
    }

}
