package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.ItemCatalog;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public abstract class Strategy {

    public BiConsumer<Item, Integer> incrementQuality = this::incrementQuality;
    public BiConsumer<Item, Integer> decrementQuality = this::decrementQuality;
    public Predicate<Item> isExpired = item -> item.sellIn <= 0;
    public Consumer<Item> nullifyQuality = item -> item.quality = 0;

    public void updateItem(Item item) {
        updateQuality(item);
        if (!item.name.equals(ItemCatalog.SULFURAS.label)) {
            item.sellIn--;
        }
    };

    public abstract void updateQuality(Item item);

    private Predicate<Item> isQualityAboveZero = item -> item.quality > 0;
    private Predicate<Item> isQualityBelowFifty = item -> item.quality < 50;

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
}
