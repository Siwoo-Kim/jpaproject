package com.siwoo.application.repository;

import com.siwoo.application.domain.Client;
import com.siwoo.application.domain.Client_;
import com.siwoo.application.domain.Order;
import com.siwoo.application.domain.Order_;
import com.siwoo.application.domain.criteria.OrderCriteria;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository @Transactional(readOnly = true)
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override @Transactional(readOnly = false)
    public void save(Order order) {
        if(order.getId() == null) {
            entityManager.persist(order);
        } else {
            entityManager.merge(order);
        }
    }

    @Override
    public Order findById(Long id) {
        return entityManager.find(Order.class,id);
    }

    private static final String FIND_ALL = "select o from Orders o ";
    @Override
    public List<Order> findAll() {
        return entityManager.createQuery(FIND_ALL,Order.class).getResultList();
    }

    @Override
    public List<Order> findAll(OrderCriteria orderCriteria) {
        Class orderClass = Order.class;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(orderClass);
        Root<Order> orderRoot = cq.from(orderClass);

        List<Predicate> criteria = new ArrayList<>();

        String clientName = orderCriteria.getClientName();
        Order.Status status = orderCriteria.getStatus();
        if(status != null) {
            Predicate predicate = cb.equal(orderRoot.get(Order_.status),status);
            criteria.add(predicate);
        }

        if(StringUtils.hasText(clientName)) {
            Join<Order, Client> join = orderRoot.join(Order_.client,JoinType.INNER);
            Predicate predicate = cb.like(join.get(Client_.nickname),"%" + clientName + "%" );
            criteria.add(predicate);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        return entityManager.createQuery(cq).setMaxResults(1000).getResultList();
    }



}
