package cz.cvut.fel.ts1.storage;

import cz.cvut.fel.ts1.shop.*;


/**
 * Auxiliary class for item storage
 */
public class ItemStock {
    private Item refItem;
    private int count;

    //was not public
    public ItemStock(Item refItem) {
        this.refItem = refItem;
        if (refItem == null) {
            throw new NullPointerException();
        }
        count = 0;
    }

    @Override
    public String toString() {
        return "STOCK OF ITEM:  " + refItem.toString() + "    PIECES IN STORAGE: " + count;
    }

    public void IncreaseItemCount(int x) {
        count += x;
    }

    // can not be less than 0?
    public void decreaseItemCount(int x) throws NoItemInStorage {
        if (count < x) {
            throw new NoItemInStorage();
        } else {
            count -= x;
        }

    }

    public int getCount() {
        return count;
    }

    public Item getItem() {
        return refItem;
    }
}