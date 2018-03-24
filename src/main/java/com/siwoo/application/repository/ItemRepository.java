package com.siwoo.application.repository;

import com.siwoo.application.domain.Item;

import java.util.List;

public interface ItemRepository {

    void save(Item item);

    List<Item> findAll();

    Item findById(Long id);
}
