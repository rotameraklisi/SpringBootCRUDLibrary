package com.example.library.service;

import com.example.library.entity.Item;
import com.example.library.exceptions.DuplicateItemException;
import com.example.library.exceptions.ItemNotFoundException;
import com.example.library.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemsRepository;

    @Override
    public Item save(Item item) {
        return itemsRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemsRepository.findAll();
    }

    @Override
    public Item findById(Long itemId) throws ItemNotFoundException{
        return itemsRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public Item findByIdAndType( Long itemId, int type) {
        return itemsRepository.findByIdAndType(itemId, type);
    }

    @Override
    public Item findByTypeAndName(int type, String name) {
        return itemsRepository.findByTypeAndName(type, name);
    }

    @Override
    public List<Item> findByTypeAndFlag(int type, Boolean flag) {
        return itemsRepository.findByTypeAndFlag(type, flag);
    }

    @Override
    public List<Item> findAllByType(int type){
        return itemsRepository.findAllByType(type);
    }

    @Override
    public void deleteById(Long itemId) {
        itemsRepository.deleteById(itemId);
    }

    @Override
    public Item updateById(Item item) {
        return itemsRepository.save(item);
    }



}
