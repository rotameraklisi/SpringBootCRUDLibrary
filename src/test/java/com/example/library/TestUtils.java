package com.example.library;

import com.example.library.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Item generateItem() {
        return new Item(1L, "Miserables", true, 0);
    }

    public static List<Item> generateItems() {
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item(1L, "Miserables", true, 0);
        Item item2 = new Item(2L, "New York Times", false, 1);
        itemList.add(item1);
        itemList.add(item2);
        return itemList;
    }



}
