package com.example.library.controller;

import com.example.library.entity.Item;
import com.example.library.exceptions.DuplicateItemException;
import com.example.library.exceptions.ItemNotFoundException;
import com.example.library.service.ItemService;
import com.example.library.utils.ConverterUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/dockerTrial")
    public String welcome() {
        return "Welcome Docker";
    }

    @PostMapping("/{type}/save")
    public ResponseEntity<Item> save(@PathVariable(value = "type") String type, @RequestBody Item item) {
        setType2Item(type, item);
        Item saved_item = itemService.save(item);
        return new ResponseEntity<Item>(saved_item, HttpStatus.CREATED);
    }

    @GetMapping("/{type}/all")
    public ResponseEntity<List<Item>> findAllByType(@PathVariable(value = "type") String type) {
        List<Item> allItemsByType = itemService.findAllByType(ConverterUtil.TypeToNumber(type));
        return new ResponseEntity<List<Item>>(allItemsByType, HttpStatus.OK);
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<Item> findByIdAndType(@PathVariable(name = "type") String type, @PathVariable(name = "id") Long itemId) throws ItemNotFoundException {
        Item item = itemService.findByIdAndType(itemId, ConverterUtil.TypeToNumber(type));
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @PutMapping("/{type}/update/{id}")
    public ResponseEntity<Item> updateById(@PathVariable(name = "type") String type, @PathVariable(name = "id") Long itemId, @RequestBody Item item) throws ItemNotFoundException {
        Item existItem = itemService.findByIdAndType(itemId, ConverterUtil.TypeToNumber(type));
        setNewValue2ExistItem(existItem, item, type);
        Item updateItem = itemService.updateById(existItem);
        return new ResponseEntity<Item>(updateItem, HttpStatus.OK);
    }

    @DeleteMapping("/{type}/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long itemId) throws ItemNotFoundException {
        itemService.deleteById(itemId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{type}/count")
    public ResponseEntity<Integer> findCountByType(@PathVariable(name = "type") String type) throws ItemNotFoundException {
        int count = itemService.findAllByType(ConverterUtil.TypeToNumber(type)).size();
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @GetMapping("/{type}/{name}/status")
    public ResponseEntity<String> findStatusByTypeAndName(@PathVariable(value = "type") String type, @PathVariable(value = "name") String name) throws ItemNotFoundException {
        boolean status = itemService.findByTypeAndName(ConverterUtil.TypeToNumber(type), name).isFlag();
        String response = "available:" + status;
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/items/all")
    public ResponseEntity<List<Item>> findAll() throws ItemNotFoundException {
        List<Item> allItems = itemService.findAll();
        return new ResponseEntity<List<Item>>(allItems, HttpStatus.OK);
    }

    @GetMapping("/items/count")
    public ResponseEntity<String> findCount() throws ItemNotFoundException {
        int totalCount = itemService.findAll().size();
        String response = "total count=" + totalCount;
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<String> findByTypeAndFlag(@RequestParam(value = "type") String type, @RequestParam(value = "available") Boolean flag) throws ItemNotFoundException {
        int totalCount = itemService.findByTypeAndFlag(ConverterUtil.TypeToNumber(type), flag).size();
        String response = "total count=" + totalCount;
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    private void setType2Item(String type, Item item) {
        item.setType(ConverterUtil.TypeToNumber(type));
    }

    private void setNewValue2ExistItem(Item existItem, Item item, String type) {
        existItem.setName(item.getName());
        existItem.setType(item.getType());
        existItem.setFlag(item.isFlag());
        setType2Item(type, item);
    }

}
