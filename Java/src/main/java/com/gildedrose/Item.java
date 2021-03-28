package com.gildedrose;

import com.gildedrose.strategy.*;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    private Strategy strategy;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.setStrategy(name);
        this.sellIn = sellIn;
        this.quality = quality;
    }

    private void setStrategy(String name) {
        if (name.equals(ItemCatalog.AGED_BRIE.label))
            this.strategy = new IncrementerStrategy();
        else if (name.equals(ItemCatalog.BACKSTAGE_PASSES.label))
            this.strategy = new BackstageStrategy();
        else if (name.equals(ItemCatalog.SULFURAS.label))
            this.strategy = new LegendaryStrategy();
        else
            this.strategy = new DecrementerStrategy();
    }

    public void updateItem() {
        this.strategy.updateItem(this);
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
