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
    void qualityPositive() {
        Item[] items = new Item[] { new Item("foo", 1, 10) };
        updateQuality(items);
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void qualityZero() {
        Item[] items = new Item[] { new Item("foo", 1, 0) };
        updateQuality(items);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void qualityNegative() {
        Item[] items = new Item[] { new Item("foo", 1, -10) };
        updateQuality(items);
        assertEquals(-10, app.items[0].quality);
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

    private void updateQuality(Item[] items) {
        app = new GildedRose(items);
        app.updateQuality();
    }
}
