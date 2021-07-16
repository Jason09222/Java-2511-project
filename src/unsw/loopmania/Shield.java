package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Shield in the backend world
 */
public class Shield extends BasicItem {
    // TODO = add more weapon/item types
    private final int defense = 200;
    private final int price = 2000;
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "Shield");
    }
    public int getDefense() {
        return this.defense;
    }
    public int getPrice() {
        return this.price;
    }
}