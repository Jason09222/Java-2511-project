package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Staff in the backend world
 */
public class Staff extends BasicItem {
    private final int damage = 100;
    private final int price = 2000;
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "Staff");
    }
    public int getDamage() {
        return damage;
    }
    public int getPrice() {
        return price;
    }
}