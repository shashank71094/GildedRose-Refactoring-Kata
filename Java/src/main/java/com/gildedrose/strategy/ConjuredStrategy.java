package com.gildedrose.strategy;

import com.gildedrose.Item;

public class ConjuredStrategy extends Strategy {

    @Override
    public void updateQuality(Item item) {
        decrementQuality.accept(item, 2);
        if (isExpired.test(item)) {
            decrementQuality.accept(item, 2);
        }
    }
}
