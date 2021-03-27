package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .map(item -> item.setSellInTime())
                .map(item -> item.updateItem())
                .forEach(item -> {});
    }
}