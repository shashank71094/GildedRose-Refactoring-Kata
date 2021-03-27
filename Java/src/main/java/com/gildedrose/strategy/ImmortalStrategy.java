package com.gildedrose.strategy;

import com.gildedrose.Item;

public class ImmortalStrategy implements strategy {
    @Override
    public Item setQuality(Item item) {
        return item;
    }
}
