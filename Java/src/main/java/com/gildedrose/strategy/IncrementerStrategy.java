package com.gildedrose.strategy;

import com.gildedrose.Item;

public class IncrementerStrategy extends Strategy {

    @Override
    public void updateItem(Item item) {
        if (isQualityBelowFifty.test(item)) {
            item.quality++;
            if(isExpired.and(isQualityBelowFifty).test(item)) {
                item.quality++;
            }
        }
        item.sellIn--;
    }
}
