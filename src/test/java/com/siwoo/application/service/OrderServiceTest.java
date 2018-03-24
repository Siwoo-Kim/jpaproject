package com.siwoo.application.service;

import com.siwoo.application.domain.*;
import com.siwoo.application.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/app-context.xml")
public class OrderServiceTest {

    @PersistenceContext EntityManager entityManager;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    private static List<Client> clientFixtures;
    private static List<Item> itemFixtures;

    @Before
    public void setup() {
        clientFixtures = new ArrayList<>();
        itemFixtures = new ArrayList<>();
        Client client = new Client();
        client.setNickname("siwooKing1");
        client.setAddress(new Address("Toronto","Altamontd Dr","msj s3s"));
        clientFixtures.add(client);

        Book book = new Book();
        book.setName("JPA PROJECT");
        book.setAuthor("Noone");
        book.setIsbn("12342");
        book.setPrice(132);
        book.setStockQuantity(10);
        itemFixtures.add(book);

    }

    @Test
    public void makeOrder() {

        Client client = clientFixtures.get(0);
        entityManager.persist(client);
        Item item = itemFixtures.get(0);
        int stockBeforeOrder = item.getStockQuantity();
        int orderCount = 2;
        entityManager.persist(item);

        Order order = orderService.makeOrder(client,item,orderCount);
        assertNotNull(order);
        assertNotNull(order.getClient());
        assertNotNull(order.getDelivery());

        Order foundOrder = orderRepository.findById(order.getId());
//        log.warn(order.toString());
//        log.warn(order.getOrderItems().toString());
//        log.warn(order.getDelivery().toString());

        assertThat(Order.Status.ORDER, is(equalTo(foundOrder.getStatus())));
        assertEquals(1,foundOrder.getOrderItems().size());
        assertEquals(item.getPrice() * orderCount, foundOrder.getPrice());
        assertEquals(stockBeforeOrder - orderCount, item.getStockQuantity());
    }

    @Test(expected = Item.NotEnoughQuantityException.class)
    public void exceedStockQuantity() {
        Client client = clientFixtures.get(0);
        entityManager.persist(client);
        Item item = itemFixtures.get(0);
        int stockBeforeOrder = item.getStockQuantity();
        int orderCount = 20;
        entityManager.persist(item);

        Order order = orderService.makeOrder(client,item,orderCount);
        fail("expect not enough stock exception");
    }

    @Test
    public void cancel() {
        Client client = clientFixtures.get(0);
        entityManager.persist(client);
        Item item = itemFixtures.get(0);
        int beforeSize = item.getStockQuantity();
        int orderCount = 2;
        entityManager.persist(item);

        Order order = orderService.makeOrder(client,item,orderCount);
        Order foundOrder = orderRepository.findById(order.getId());

        assertEquals(beforeSize-orderCount,item.getStockQuantity());
        orderService.cancelOrder(foundOrder);
        assertEquals(Order.Status.CANCEL, foundOrder.getStatus());
        assertEquals(beforeSize,item.getStockQuantity());

    }
}
