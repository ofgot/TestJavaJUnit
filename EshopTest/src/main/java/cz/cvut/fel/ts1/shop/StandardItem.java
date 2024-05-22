package cz.cvut.fel.ts1.shop;


/**
 * The class for standard item in the EShop.
 */


public class StandardItem extends Item {

    private int loyaltyPoints;

    public StandardItem(int id, String name, double price, String category, int loyaltyPoints) {
        super(id, name, price, category);
        this.loyaltyPoints = loyaltyPoints;
        if (loyaltyPoints < 0){
            throw new IllegalArgumentException("Loyalty points can not be negative");
        }
    }

    public String toString() {
        return super.toString() + "   PRICE: " + getPrice() + "   LOYALTY POINTS: " + loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean equals(Object object) {
        if (object instanceof StandardItem) {
            StandardItem zbozi = (StandardItem) object;
            return (super.equals(zbozi) && loyaltyPoints == zbozi.getLoyaltyPoints());
        }
        return false;
    }

    public StandardItem copy() {
        return new StandardItem(getID(), getName(), getPrice(), getCategory(), getLoyaltyPoints());
    }
}
