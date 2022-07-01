package com.example.library.service;

import java.util.List;

import com.example.library.entity.Item;
import com.example.library.exceptions.DuplicateItemException;
import com.example.library.exceptions.ItemNotFoundException;

public interface ItemService {

    Item save(Item item);

    List<Item> findAll();

    Item findById(Long itemId) throws ItemNotFoundException;

    Item findByIdAndType( Long itemId, int type);

    Item findByTypeAndName(int type, String name);

    List<Item> findByTypeAndFlag(int type, Boolean flag);

    List<Item> findAllByType(int type);

    void deleteById(Long itemId);

    Item updateById(Item item);


}
