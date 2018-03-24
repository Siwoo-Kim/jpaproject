package com.siwoo.application.service;

import com.siwoo.application.domain.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemService {

    @Transactional(readOnly = false)
    void reserveItem(Item item);

    List<Item> findItems();

    Item find(Long id);
}
