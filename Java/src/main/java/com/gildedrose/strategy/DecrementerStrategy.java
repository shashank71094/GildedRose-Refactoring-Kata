package com.gildedrose.strategy;

import com.gildedrose.Item;

public class DecrementerStrategy extends Strategy {

    @Override
    public void updateItem(Item item) {
        if (isQualityAboveZero.test(item)) {
            item.quality--;
            if(isExpired.and(isQualityAboveZero).test(item)) {
                item.quality--;
            }
        }
        item.sellIn--;
    }
}
