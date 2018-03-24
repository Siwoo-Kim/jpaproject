package com.siwoo.application.service;

import com.siwoo.application.domain.Item;
import com.siwoo.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service @Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    @Autowired ItemRepository itemRepository;

    @Override @Transactional(readOnly = false)
    public void reserveItem(Item item) {
        Assert.notNull(item,"item must not null");
        Assert.notNull(item, "item must not contains id");
        itemRepository.save(item);
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item find(Long id) {
        return itemRepository.findById(id);
    }
}
