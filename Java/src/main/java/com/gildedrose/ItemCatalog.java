package com.gildedrose;

public enum ItemCatalog {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    CONJURED("Conjured");

    public final String label;

    ItemCatalog(String label) { this.label = label; }
}
