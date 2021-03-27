package com.gildedrose.strategy;

import com.gildedrose.Item;

public class NormalStrategy implements strategy{
    @Override
    public Item setQuality(Item item) {
        System.out.println(item.sellIn);
        if (item.quality > 0) {
            item.quality--;
            if(item.sellIn < 0 && item.quality > 0) {
                item.quality--;
            }
        }
        return item;
    }
}
