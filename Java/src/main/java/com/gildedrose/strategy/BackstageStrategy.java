package com.gildedrose.strategy;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class BackstageStrategy extends Strategy {

    Predicate<Item> isConcertTenOrLessDaysAway = item -> item.sellIn <= 10;
    Predicate<Item> isConcertFiveOrLessDaysAway = item -> item.sellIn <= 5;

    @Override
    public void updateItem(Item item) {
        if (isQualityBelowFifty.test(item)) {
            item.quality++;
            if (isConcertTenOrLessDaysAway.and(isQualityBelowFifty).test(item)) {
                item.quality++;
            }
            if (isConcertFiveOrLessDaysAway.and(isQualityBelowFifty).test(item)) {
                item.quality++;
            }
            if(isExpired.test(item)) {
                item.quality = 0;
            }
        }
        item.sellIn--;
    }
}
