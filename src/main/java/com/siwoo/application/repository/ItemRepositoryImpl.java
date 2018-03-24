package com.siwoo.application.repository;

import com.siwoo.application.domain.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository @Transactional(readOnly = true)
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Item item) {
        if(item.getId() == null) {
            entityManager.persist(item);
        } else {
            entityManager.merge(item);
        }
    }

    private static final String FIND_ALL = "select i from Item i ";
    @Override
    public List<Item> findAll() {
        return entityManager.createQuery(FIND_ALL,Item.class).getResultList();
    }

    @Override
    public Item findById(Long id) {
        return entityManager.find(Item.class,id);
    }

}
