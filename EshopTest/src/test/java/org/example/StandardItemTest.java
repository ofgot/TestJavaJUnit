package org.example;


import cz.cvut.fel.ts1.shop.StandardItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StandardItemTest {

    @Test
    public void ConstructorStandardItemTest(){
        StandardItem standardItem = new StandardItem(1, "Test item", 123.0, "Test category", 3);
        String expectedValue = "Item   ID: 1   NAME: Test item   CATEGORY: Test category   PRICE: 123.0   LOYALTY POINTS: 3";
        assertEquals(expectedValue, standardItem.toString());
    }

    @Test
    public void ConstructorStandardItemTestNegativeLoyaltyPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StandardItem(2, "Test item2", 33.0, "Test category", -3);
        });
    }

    @Test
    public void ConstructorStandardItemTestNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StandardItem(3, "Test item2", -55, "Test category", 4);
        });
    }

    @Test
    public void StandardItemCopyTest(){
        StandardItem standardItem = new StandardItem(4, "Test item", 1000.9, "Test category", 6);
        StandardItem copiedStandardItem = standardItem.copy();

        assertNotNull(copiedStandardItem);

        assertEquals(copiedStandardItem.getID(), standardItem.getID());
        assertEquals(copiedStandardItem.getName(), standardItem.getName());
        assertEquals(copiedStandardItem.getPrice(), standardItem.getPrice(), 0.001);
        assertEquals(copiedStandardItem.getCategory(), standardItem.getCategory());
        assertEquals(copiedStandardItem.getLoyaltyPoints(), standardItem.getLoyaltyPoints());
    }

    static Stream<Arguments> localParameters() {
        return Stream.of(
                Arguments.of(
                        new StandardItem(1, "Test item", 1000, "Test category", 6),
                        new StandardItem(1, "Test item", 1000, "Test category", 6),
                        true
                ),
                Arguments.of(
                        new StandardItem(1, "Test item", 1000, "Test category", 6),
                        new StandardItem(3, "Test item", 1000, "Test category", 6),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Test item", 1000, "Test category", 3),
                        new StandardItem(3, "Test item", 1000, "Test category", 6),
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("localParameters")
    public void StandardItemEqualsTest(StandardItem first, StandardItem second, Boolean result){
        assertEquals(result, first.equals(second));
    }
}
