package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Helmet in the backend world
 */
public class Helmet extends BasicItem {
    private final int defense = 150;
    private final int price = 1500;
    // TODO = add more weapon/item types
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.HELMET);
    }
    public int getDefense() {
        return this.defense;
    }
    public int getPrice() {
        return this.price;
    }
    
}