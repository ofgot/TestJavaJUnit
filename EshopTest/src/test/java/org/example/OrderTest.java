package org.example;


import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void OrderWithStateTest(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(1, "Test item1", 123.0, "Test category"));
        shoppingCart.addItem(new Item(2, "Test item2", 13, "Test category"));

        Order order = new Order(shoppingCart, "Name", "Customer address", 1);

        String expectedName = "Name";
        String expectedAddress = "Customer address";
        int expectedState = 1;

        assertEquals(expectedName, order.getCustomerName());
        assertEquals(expectedAddress, order.getCustomerAddress());
        assertEquals(expectedState, order.getState());
    }

    @Test
    public void OrderCheckCustomerNameAddressStateShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(1, "Test item1", 123.0, "Test category"));
        shoppingCart.addItem(new Item(2, "Test item2", 13, "Test category"));

        Order order = new Order(shoppingCart, "Name", "Customer address");

        String nameResult = "Name";
        String customerAddressResult = "Customer address";
        int expectedAddress = 0;

        assertNotNull(order);
        assertEquals(shoppingCart.getCartItems(), order.getItems());
        assertEquals(nameResult, order.getCustomerName());
        assertEquals(customerAddressResult, order.getCustomerAddress());
        assertEquals(expectedAddress, order.getState());
    }


    @Test
    public void OrderCheckNullNameAndAddressShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(1, "Test item1", 123.0, "Test category"));
        shoppingCart.addItem(new Item(2, "Test item2", 13, "Test category"));

        assertThrows(NullPointerException.class, () -> {
            new Order(shoppingCart, null, null);
        });

        assertThrows(NullPointerException.class, () -> {
            new Order(shoppingCart, "Customer Name", null);
        });

        assertThrows(NullPointerException.class, () -> {
            new Order(shoppingCart, null, "Customer Address");
        });

        assertThrows(NullPointerException.class, () -> {
            new Order(null, "Name", "Customer address");
        });
    }
}
