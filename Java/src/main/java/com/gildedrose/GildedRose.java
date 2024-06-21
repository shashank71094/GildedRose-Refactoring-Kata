package com.gildedrose;

import com.gildedrose.strategy.*;

import java.util.Arrays;

class GildedRose {

    Item[] items;
    Strategy strategy;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .peek(this::setStrategy)
                .forEach(this::updateItem);
    }

    private void setStrategy(Item item) {
        String name = item.name;
        if (name.equals(ItemCatalog.AGED_BRIE.label))
            this.strategy = new IncrementerStrategy();
        else if (name.equals(ItemCatalog.BACKSTAGE_PASSES.label))
            this.strategy = new BackstageStrategy();
        else if (name.equals(ItemCatalog.SULFURAS.label))
            this.strategy = new LegendaryStrategy();
        else if (name.equals(ItemCatalog.CONJURED.label))
            this.strategy = new ConjuredStrategy();
        else
            this.strategy = new DecrementerStrategy();
    }

    private void updateItem(Item item) {
        this.strategy.updateItem(item);
    }
}