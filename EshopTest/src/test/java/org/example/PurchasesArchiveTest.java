package org.example;

import cz.cvut.fel.ts1.archive.ItemPurchaseArchiveEntry;
import cz.cvut.fel.ts1.archive.PurchasesArchive;
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PurchasesArchiveTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testItemPurchaseArchiveEntry() {
        Item item = mock(Item.class);
        ItemPurchaseArchiveEntry purchasesArchiveEntry = new ItemPurchaseArchiveEntry(item);

        assertEquals(1, purchasesArchiveEntry.getSoldCount());
        assertEquals(item, purchasesArchiveEntry.getRefItem());
    }

    @Test
    public void testPrintItemPurchaseStatistics() {
        PurchasesArchive purchasesArchive = new PurchasesArchive();

        purchasesArchive.itemPurchaseArchive.put(1, new ItemPurchaseArchiveEntry(new Item(1, "Test item1", 123.0, "Test category")));
        purchasesArchive.itemPurchaseArchive.put(2, new ItemPurchaseArchiveEntry(new Item(2, "Test item2", 13, "Test category")));
        purchasesArchive.itemPurchaseArchive.get(2).increaseCountHowManyTimesHasBeenSold(5);

        purchasesArchive.printItemPurchaseStatistics();

        String expectedOutput1 = "ITEM PURCHASE STATISTICS:\n" +
                "ITEM  Item   ID: 1   NAME: Test item1   CATEGORY: Test category   HAS BEEN SOLD 1 TIMES\n" +
                "ITEM  Item   ID: 2   NAME: Test item2   CATEGORY: Test category   HAS BEEN SOLD 6 TIMES\n";
        assertEquals(expectedOutput1, outContent.toString());
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldTest() {
        Item item = new Item(1, "Test item1", 123.0, "Test category");

        ItemPurchaseArchiveEntry itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(item);
        itemPurchaseArchiveEntry.increaseCountHowManyTimesHasBeenSold(5);

        HashMap<Integer, ItemPurchaseArchiveEntry> hashMap = new HashMap<>();
        hashMap.put(1, itemPurchaseArchiveEntry);

        PurchasesArchive purchasesArchive = new PurchasesArchive(hashMap, null);

        int expectedValue = 6;

        assertEquals(expectedValue, purchasesArchive.getHowManyTimesHasBeenItemSold(item));
    }

    @Test
    public void testGetHowManyTimesHasBeenItemSoldZeroSales() {
        PurchasesArchive purchasesArchive = new PurchasesArchive();
        Item item = new Item(1, "Test item1", 123.0, "Test category");

        int expectedSOLD = 0;

        assertEquals(expectedSOLD, purchasesArchive.getHowManyTimesHasBeenItemSold(item));
    }


    @Test
    public void testPutOrderToPurchasesArchive() {
        PurchasesArchive purchasesArchive = new PurchasesArchive();

        ShoppingCart cart = new ShoppingCart();
        Item item1 = new Item(1, "Test item1", 123.0, "Test category");
        Item item2 = new Item(2, "Test item2", 456.0, "Test category");
        cart.addItem(item1);
        cart.addItem(item2);

        Order order = new Order(cart, "Test Customer", "Test Address");

        purchasesArchive.putOrderToPurchasesArchive(order);
        purchasesArchive.itemPurchaseArchive.get(2).increaseCountHowManyTimesHasBeenSold(3);

        int expectedSize = 2;
        int expectedSoldAmount = 1;
        int expectedSecondSoldAmount = 4;

        assertEquals(expectedSize, purchasesArchive.itemPurchaseArchive.size());
        assertEquals(expectedSoldAmount, purchasesArchive.getHowManyTimesHasBeenItemSold(item1));
        assertEquals(expectedSecondSoldAmount, purchasesArchive.getHowManyTimesHasBeenItemSold(item2));
    }

    @Test
    public void orderArchiveConstructorMockTest() {
        HashMap<Integer, ItemPurchaseArchiveEntry> map = mock(HashMap.class);
        ArrayList<Order> array = mock(ArrayList.class);
        PurchasesArchive purchasesArchive = new PurchasesArchive(map, array);

        assertEquals(map, purchasesArchive.itemPurchaseArchive);
        assertEquals(array, purchasesArchive.orderArchive);
    }

    @Test
    public void itemPurchaseArchiveEntryConstructorMockTest() {
        Item item = mock(Item.class);
        ItemPurchaseArchiveEntry itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(item);

        int expectedSoldCount = 1;

        assertEquals(item, itemPurchaseArchiveEntry.getRefItem());
        assertEquals(expectedSoldCount, itemPurchaseArchiveEntry.getSoldCount());
    }

    @Test
    public void increaseCountHowManyTimesHasBeenSoldTest(){
        Item item = mock(Item.class);
        ItemPurchaseArchiveEntry itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(item);

        itemPurchaseArchiveEntry.increaseCountHowManyTimesHasBeenSold(10);

        int expectedSoldCount = 11;

        assertEquals(expectedSoldCount, itemPurchaseArchiveEntry.getSoldCount());
    }

    @Test
    public void toStringTest(){
        Item item = mock(Item.class);
        when(item.toString()).thenReturn("Item   ID: 1   NAME: Name   CATEGORY: Category");

        String expectedString = "ITEM  Item   ID: 1   NAME: Name   CATEGORY: Category   HAS BEEN SOLD 1 TIMES";

        ItemPurchaseArchiveEntry itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(item);
        assertEquals(expectedString, itemPurchaseArchiveEntry.toString());
    }

}
