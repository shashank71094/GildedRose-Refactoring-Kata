package com.gildedrose;

import com.gildedrose.strategy.*;

import java.util.Arrays;
import java.util.List;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public strategy qualityStrategy;


    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;

        if(name.equals("Aged Brie")) {
            this.qualityStrategy = new AgedStrategy();
        } else if(name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            this.qualityStrategy = new BackstageStrategy();
        } else if(name.equals("Sulfuras, Hand of Ragnaros")) {
            this.qualityStrategy = new ImmortalStrategy();
        } else {
            this.qualityStrategy = new NormalStrategy();
        }

    }

    public Item setSellInTime() {
        if(!this.name.equals("Sulfuras, Hand of Ragnaros")) {
            this.sellIn--;
        }
        return this;
    }

    public Item updateItem() {
        this.qualityStrategy.setQuality(this);
        return this;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
