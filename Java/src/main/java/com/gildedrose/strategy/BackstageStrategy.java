package com.gildedrose.strategy;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class BackstageStrategy extends Strategy {

    Predicate<Item> isConcertTenOrLessDaysAway = item -> item.sellIn <= 10;
    Predicate<Item> isConcertFiveOrLessDaysAway = item -> item.sellIn <= 5;

    @Override
    public void updateQuality(Item item) {
        incrementQuality.accept(item, 1);
        if (isConcertTenOrLessDaysAway.test(item)) {
            incrementQuality.accept(item, 1);
        }
        if (isConcertFiveOrLessDaysAway.test(item)) {
            incrementQuality.accept(item, 1);
        }
        if (isExpired.test(item)) {
            nullifyQuality.accept(item);
        }
    }
}
