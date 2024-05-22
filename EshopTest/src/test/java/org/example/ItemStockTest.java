package org.example;

import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.storage.ItemStock;
import cz.cvut.fel.ts1.storage.NoItemInStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;

public class ItemStockTest {

    @Test
    public void itemStockTestConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Item item = new Item(1, "Item name", 10092.3, "Item category");
        ItemStock itemStock = new ItemStock(item);

        int expectedCount = 0;
        Item expectedItem = new Item (1, "Item name", 10092.3, "Item category");

        Assertions.assertEquals(expectedCount, itemStock.getCount());
        Assertions.assertEquals(expectedItem, itemStock.getItem());
    }

    @Test
    public void itemStockTestConstructorNullRefItem() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new ItemStock(null);
        });
    }

    @ParameterizedTest
    @CsvSource({"0, 3, 3", "10, 5, 15", "2000, 1, 2001"})
    void testIncreaseItemCount(int actualAmount, int increaseAmount, int expectedResult) {
        Item item = new Item(1, "Item name", 10092.3, "Item category");
        ItemStock itemStock = new ItemStock(item);

        itemStock.IncreaseItemCount(actualAmount);
        itemStock.IncreaseItemCount(increaseAmount);
        Assertions.assertEquals(expectedResult, itemStock.getCount());
    }

    @ParameterizedTest
    @CsvSource({"1, 1, 0", "10, 5, 5", "0, 0, 0"})
    void testDecreaseItemCount(int actualAmount, int decreaseAmount, int expectedResult) throws NoItemInStorage {
        Item item = new Item(1, "Item name", 10092.3, "Item category");
        ItemStock itemStock = new ItemStock(item);

        itemStock.IncreaseItemCount(actualAmount);
        itemStock.decreaseItemCount(decreaseAmount);
        Assertions.assertEquals(expectedResult, itemStock.getCount());
    }

    @ParameterizedTest
    @CsvSource({"0, 1", "5, 6", "0, 100"})
    void testDecreaseNegativeItemCount(int actualAmount, int decreaseAmount) throws NoItemInStorage {
        Item item = new Item(1, "Item name", 10092.3, "Item category");
        ItemStock itemStock = new ItemStock(item);

        itemStock.IncreaseItemCount(actualAmount);
        Assertions.assertThrows(NoItemInStorage.class, () ->{
            itemStock.decreaseItemCount(decreaseAmount);
        });
    }

}
