package cz.cvut.fel.ts1.archive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import cz.cvut.fel.ts1.shop.*;


/**
 * Class for archive of the purchases
 */
public class PurchasesArchive {
    public final HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive;
    public final ArrayList<Order> orderArchive;

    public PurchasesArchive() {
        itemPurchaseArchive = new HashMap<>();
        orderArchive = new ArrayList<>();
    }

    public PurchasesArchive(HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive, ArrayList<Order> orderArchive) {
        this.itemPurchaseArchive = itemArchive;
        this.orderArchive = orderArchive;
    }

    public void printItemPurchaseStatistics() {
        Collection<ItemPurchaseArchiveEntry> itemEntries = itemPurchaseArchive.values();
        System.out.println("ITEM PURCHASE STATISTICS:");
        for (ItemPurchaseArchiveEntry e : itemEntries) {
            System.out.println(e.toString());
        }
    }

    public int getHowManyTimesHasBeenItemSold(Item item) {
        if (itemPurchaseArchive.containsKey(item.getID())) {
            return itemPurchaseArchive.get(item.getID()).getCountHowManyTimesHasBeenSold();
        }
        return 0;
    }


    public void putOrderToPurchasesArchive(Order order) {
        orderArchive.add(order);
        ArrayList<Item> orderItems = order.getItems();
        for (Item i : orderItems) {
            if (itemPurchaseArchive.containsKey(i.getID())) {
                ItemPurchaseArchiveEntry e = itemPurchaseArchive.get(i.getID());
                e.increaseCountHowManyTimesHasBeenSold(1);
            } else {
                itemPurchaseArchive.put(i.getID(), new ItemPurchaseArchiveEntry(i));
            }
        }
    }

    public HashMap<Integer, ItemPurchaseArchiveEntry> getItemPurchaseArchive() {
        return itemPurchaseArchive;
    }

    public ArrayList<Order> getOrderArchive() {
        return orderArchive;
    }
}

