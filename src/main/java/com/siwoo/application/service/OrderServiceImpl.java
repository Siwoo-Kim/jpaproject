package com.siwoo.application.service;

import com.siwoo.application.domain.*;
import com.siwoo.application.repository.ClientRepository;
import com.siwoo.application.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service @Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Autowired ClientRepository clientRepository;
    @Autowired OrderRepository orderRepository;
    @Autowired ItemService itemService;

    @Transactional(readOnly = false)
    @Override
    public Order makeOrder(Client client, Item item, int count) {
        Assert.notNull(client,"client must not null");
        Assert.notNull(client.getId(),"client id must exists");
        Assert.notNull(item,"item must not null");
        Assert.notNull(client.getId(),"item id must exists");
        client = clientRepository.findById(client.getId());
        item = itemService.find(item.getId());
        Delivery delivery = new Delivery(client.getAddress());
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);
        Order order = Order.createOrder(client,delivery,orderItem);
        orderRepository.save(order);
        return order;
    }

    @Transactional(readOnly = false)
    @Override
    public Order makeOrder(long clientId, long itemId, int count) {
        return makeOrder(clientRepository.findById(clientId),itemService.find(itemId),count);
    }

    @Transactional(readOnly = false)
    @Override
    public void cancelOrder(Order order) {
        Assert.notNull(order,"order must not null");
        Assert.notNull(order.getId(),"order id must exists");
        order = orderRepository.findById(order.getId());
        order.cancel(); //dirty check
    }

    @Transactional(readOnly = false)
    @Override
    public void cancelOrder(long orderId) {
        cancelOrder(orderRepository.findById(orderId));
    }
}
