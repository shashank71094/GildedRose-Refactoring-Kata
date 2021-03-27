package com.gildedrose.strategy;

import com.gildedrose.Item;

public class AgedStrategy implements strategy{
    @Override
    public Item setQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
            if(item.sellIn < 0 && item.quality < 50) {
                item.quality++;
            }
        }


        return item;
    }
}
