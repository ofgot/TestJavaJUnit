package org.example;

import cz.cvut.fel.ts1.shop.*;
import cz.cvut.fel.ts1.storage.NoItemInStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class EshopControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        EShopController.startEShop();
    }
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    public void testStartEshopNotNull() {
        assertNotNull(EShopController.getStorage());
        assertNotNull(EShopController.getArchive());
        assertNotNull(EShopController.getCarts());
        assertNotNull(EShopController.getOrders());
    }

    @Test
    public void testStartEshopEmptyCard() throws NoItemInStorage {
        ShoppingCart emptyCart = new ShoppingCart();

        EShopController.purchaseShoppingCart(emptyCart, "Name", "Address");
        String expectedOutput = "Error: shopping cart is empty\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testAddItemToCart() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item(1, "TestItem", 50, "TestCategory");

        cart.addItem(item);

        assertTrue(cart.containItem(item.getID()));
    }

    @Test
    public void testRemoveItemToCart() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item(1, "TestItem", 50, "TestCategory");
        cart.addItem(item);

        cart.removeItem(1);
        assertFalse(cart.containItem(item.getID()));
    }


    @Test
    public void testRemoveItemFromCart() {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new Item(1, "TestItem1", 50, "TestCategory");
        Item item2 = new Item(2, "TestItem2", 4440, "TestCategory");

        cart.addItem(item1);
        cart.addItem(item2);

        cart.removeItem(item1.getID());

        assertFalse(cart.containItem(item1.getID()));
        assertTrue(cart.containItem(item2.getID()));
    }

    @Test
    public void testStartEshopNoItemInStorage(){
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item(1,"Item", 1092, "Category"));

        assertThrows(NoItemInStorage.class, ()-> {
            EShopController.purchaseShoppingCart(cart, "Name", "Address");
        });
        assertEquals(0, EShopController.getOrders().size());
    }

    @Test
    public void testStartEshopNotEnoughItemsInStorage(){
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item(3,"Item", 1092, "Category");
        cart.addItem(item);
        cart.addItem(item);

        EShopController.addToStorage(item, 1);
        assertThrows(NoItemInStorage.class, () -> {
            EShopController.purchaseShoppingCart(cart, "Name", "Address");
        });
    }

    @Test
    public void testPrice(){
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item(3,"Item", 1092, "Category");
        cart.addItem(item);
        cart.addItem(item);
        cart.addItem(item);

        double expectedPrice = 3 * 1092;

        assertEquals(expectedPrice, cart.getTotalPrice());

        EShopController.addToStorage(item, 1);
        assertThrows(NoItemInStorage.class, () -> {
            EShopController.purchaseShoppingCart(cart, "Name", "Address");
        });
    }

    @Test
    public void testDiscountPrice(){
        ShoppingCart cart = new ShoppingCart();
        DiscountedItem item = new DiscountedItem(99 ,"Item", 1092, "Category", 19, "11.10.2000", "11.10.2222");
        cart.addItem(item);

        double expectedPrice = item.getDiscountedPrice();

        assertEquals(expectedPrice, cart.getTotalPrice());

        EShopController.addToStorage(item, 1);
        assertDoesNotThrow(() -> EShopController.purchaseShoppingCart(cart, "Name", "Address"));

    }



    @Test
    public void testStartEshopTest() throws NoItemInStorage {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item(1,"Item1", 1092, "Category1"));
        cart.addItem(new Item(2,"Item2", 10, "Category2"));

        EShopController.storage.insertItems(new Item(1,"Item1", 1092, "Category1"), 4);
        EShopController.storage.insertItems(new Item(2,"Item2", 10, "Category2"), 1);

        EShopController.purchaseShoppingCart(cart, "Name", "Address");

        int expectedResultItem1 = 3;
        int expectedResultItem2 = 0;

        assertEquals(expectedResultItem1, EShopController.storage.getItemCount(1));
        assertEquals(expectedResultItem2, EShopController.storage.getItemCount(2));
        assertTrue(EShopController.getOrders().isEmpty());
    }
}
