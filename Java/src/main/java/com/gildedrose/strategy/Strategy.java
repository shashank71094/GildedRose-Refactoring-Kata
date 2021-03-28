package com.gildedrose.strategy;

import com.gildedrose.Item;

import java.util.function.Predicate;

public abstract class Strategy {

    Predicate<Item> isQualityAboveZero = item -> item.quality > 0;
    Predicate<Item> isQualityBelowFifty = item -> item.quality < 50;
    Predicate<Item> isExpired = item -> item.sellIn <= 0;

    public abstract void updateItem(Item item);
}
