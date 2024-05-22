package cz.cvut.fel.ts1.shop;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Class for shopping cart. 
 * 
 */

public class ShoppingCart {

    
    ArrayList<Item> items;

    public ShoppingCart(ArrayList<Item> items) {
        this.items = items;
    }
    
    public ShoppingCart() {
        items = new ArrayList<Item>();
    }

    /**
     * Gets items in the shopping cart.
     * @return   items in the shopping cart
     */
    public ArrayList<Item> getCartItems() {
        return items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item temp_item) {
        items.add(temp_item);
        System.out.println("Item with ID " + temp_item.getID() + " added to the shopping cart.");
    }
    public boolean containItem(int itemId) {
        if (items.isEmpty()){
            return false;
        }
        for (Item item : items){
            if (item.getID() == itemId){
                return true;
            }
        }
        return false;
    }



    
    /**
     * Removes item from the shopping chart
     * 
     * @param itemID   ID of the item to remove form the shopping chart
     */
    
    public void removeItem(int itemID) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getID() == itemID) {
                iterator.remove();
                System.out.println("Item with ID " + item.getID() + " removed from the shopping cart.");
            }
        }
    }

    public int getItemsCount() {
        return items.size();
    }

    
    /**
     * Gets total price with discount, if there are any discounted items in the chart
     * 
     * @return total price with discount
     */
    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
    
    
    
    
    
    
    
    
}
