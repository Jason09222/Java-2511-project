package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends BasicItem {
    private final int damage = 200;
    private final int price = 1000;
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.SWORD);
    }

    public int getDamage() {
        return this.damage;
    }

    public int getPrice() {
        return price;
    }
}
