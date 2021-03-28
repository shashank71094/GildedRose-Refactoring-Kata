package com.gildedrose.strategy;

import com.gildedrose.Item;

public class IncrementerStrategy extends Strategy {

    @Override
    public void updateQuality(Item item) {
        incrementQuality.accept(item, 1);
        if (isExpired.test(item)) {
            incrementQuality.accept(item, 1);
        }
    }
}
