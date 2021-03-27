package com.gildedrose.strategy;

import com.gildedrose.Item;

public class BackstageStrategy implements strategy{
    @Override
    public Item setQuality(Item item) {

        if (item.quality < 50) {
            item.quality++;
            if (item.sellIn < 11 && item.quality < 50) {
                item.quality++;
            }
            if (item.sellIn < 6 && item.quality < 50) {
                item.quality++;
            }
            if(item.sellIn < 0 && item.quality < 50) {
                item.quality = 0;
            }
        }

        return item;
    }
}
