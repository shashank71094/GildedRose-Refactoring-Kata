package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose app;

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        updateQuality(items);
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void multipleItems() {
        Item[] items = new Item[] {
                new Item("foo", 0, 0),
                new Item("bar", 0, 0),
                new Item("baz", 0, 0)
        };
        updateQuality(items);
        assertEquals("foo", app.items[0].name);
        assertEquals("bar", app.items[1].name);
        assertEquals("baz", app.items[2].name);
    }

    @Test
    void sellInDecreases() {
        Item[] items = new Item[] { new Item("foo", 1, 0) };
        updateQuality(items);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void qualityDecreases() {
        Item[] items = new Item[] { new Item("foo", 1, 10) };
        updateQuality(items);
        assertEquals(9, app.items[0].quality);
    }

    // Once the sell by date has passed, Quality degrades twice as fast

    @Test
    void sellInPositive() {
        Item[] items = new Item[] { new Item("foo", 5, 10) };
        updateQuality(items);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void sellInFinalDay() {
        Item[] items = new Item[] { new Item("foo", 1, 10) };
        updateQuality(items);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void sellInExpiryDay() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        updateQuality(items);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void sellInNegative() {
        Item[] items = new Item[] { new Item("foo", -5, 10) };
        updateQuality(items);
        assertEquals(-6, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

    // The Quality of an item is never negative

    @Test
    void qualityZeroNotExpired() {
        Item[] items = new Item[] { new Item("foo", 1, 0) };
        updateQuality(items);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityZeroExpired() {
        Item[] items = new Item[] { new Item("foo", -1, 0) };
        updateQuality(items);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityOneNotExpired() {
        Item[] items = new Item[] { new Item("foo", 1, 1) };
        updateQuality(items);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityOneExpired() {
        Item[] items = new Item[] { new Item("foo", -1, 1) };
        updateQuality(items);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityMinusOneNotExpired() {
        Item[] items = new Item[] { new Item("foo", 1, -1) };
        updateQuality(items);
        assertEquals(-1, app.items[0].quality);
    }

    @Test
    void qualityMinusOneExpired() {
        Item[] items = new Item[] { new Item("foo", -1, -1) };
        updateQuality(items);
        assertEquals(-1, app.items[0].quality);
    }

    @Test
    void qualityMinusOneForIncrementer() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, -1) };
        updateQuality(items);
        assertEquals(0, app.items[0].quality);
    }

    // “Aged Brie” actually increases in Quality the older it gets

    @Test
    void agedBrieNotExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 10) };
        updateQuality(items);
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void agedBrieExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 10) };
        updateQuality(items);
        assertEquals(12, app.items[0].quality);
    }

    // The Quality of an item is never more than 50

    @Test
    void quality50NotExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 50 ) };
        updateQuality(items);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void quality50Expired() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 50 ) };
        updateQuality(items);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void quality49NotExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 49 ) };
        updateQuality(items);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void quality49Expired() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 49 ) };
        updateQuality(items);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void quality51NotExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 51 ) };
        updateQuality(items);
        assertEquals(51, app.items[0].quality);
    }

    @Test
    void quality51Expired() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 51 ) };
        updateQuality(items);
        assertEquals(51, app.items[0].quality);
    }

    @Test
    void quality51ForDecrementer() {
        Item[] items = new Item[] { new Item("foo", 1, 51 ) };
        updateQuality(items);
        assertEquals(50, app.items[0].quality);
    }

    // “Sulfuras”, being a legendary item, never has to be sold or decreases in Quality

    @Test
    void sulfurasNotExpired() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1, 10 ) };
        updateQuality(items);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(10, app.items[0].quality);
    }

    @Test
    void sulfurasExpired() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 10 ) };
        updateQuality(items);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(10, app.items[0].quality);
    }

    /*
    * “Backstage passes”, like aged brie, increases in Quality as its SellIn value approaches
    *       Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less
    *       Quality drops to 0 after the concert
    * */

    @Test
    void backstagePassesFifteenDaysAway() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10 ) };
        updateQuality(items);
        assertEquals(11, app.items[0].quality);
    }

    @Test
    void backstagePassesTenDaysAway() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10 ) };
        updateQuality(items);
        assertEquals(12, app.items[0].quality);
    }

    @Test
    void backstagePassesFiveDaysAway() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10 ) };
        updateQuality(items);
        assertEquals(13, app.items[0].quality);
    }

    @Test
    void backstagePassesExpired() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10 ) };
        updateQuality(items);
        assertEquals(0, app.items[0].quality);
    }

    private void updateQuality(Item[] items) {
        app = new GildedRose(items);
        app.updateQuality();
    }
}
