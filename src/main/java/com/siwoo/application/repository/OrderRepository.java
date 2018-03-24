package com.siwoo.application.repository;

import com.siwoo.application.domain.Order;
import com.siwoo.application.domain.criteria.OrderCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository {
    @Transactional(readOnly = false)
    void save(Order order);

    abstract Order findById(Long id);

    List<Order> findAll();

    List<Order> findAll(OrderCriteria criteria);
}
