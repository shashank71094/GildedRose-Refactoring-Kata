package com.gildedrose.strategy;

import com.gildedrose.Item;

public class DecrementerStrategy extends Strategy {

    @Override
    public void updateQuality(Item item) {
        decrementQuality.accept(item, 1);
        if (isExpired.test(item)) {
            decrementQuality.accept(item, 1);
        }
    }
}
