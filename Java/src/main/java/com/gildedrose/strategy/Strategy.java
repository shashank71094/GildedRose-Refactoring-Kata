package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.ItemCatalog;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public abstract class Strategy {

    Predicate<Item> isQualityAboveZero = item -> item.quality > 0;
    Predicate<Item> isQualityBelowFifty = item -> item.quality < 50;
    Predicate<Item> isExpired = item -> item.sellIn <= 0;
    Consumer<Item> nullifyQuality = item -> item.quality = 0;
    BiConsumer<Item, Integer> incrementQuality = this::incrementQuality;
    BiConsumer<Item, Integer> decrementQuality = this::decrementQuality;

    private void incrementQuality(Item item, int updateBy) {
        IntStream.range(0, updateBy).forEach(i -> {
            if (isQualityBelowFifty.test(item)) {
                item.quality++;
            }
        });
    }

    private void decrementQuality(Item item, int updateBy) {
        IntStream.range(0, updateBy).forEach(i -> {
            if (isQualityAboveZero.test(item)) {
                item.quality--;
            }
        });
    }

    public void updateItem(Item item) {
        updateQuality(item);
        if (!item.name.equals(ItemCatalog.SULFURAS.label)) {
            item.sellIn--;
        }
    };

    public abstract void updateQuality(Item item);
}
