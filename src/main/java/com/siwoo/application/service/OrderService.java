package com.siwoo.application.service;

import com.siwoo.application.domain.Client;
import com.siwoo.application.domain.Item;
import com.siwoo.application.domain.Order;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
    @Transactional(readOnly = false)
    Order makeOrder(Client client, Item item, int count);

    @Transactional(readOnly = false)
    Order makeOrder(long clientId, long itemId, int count);

    @Transactional(readOnly = false)
    abstract void cancelOrder(Order order);

    @Transactional(readOnly = false)
    void cancelOrder(long orderId);
}
