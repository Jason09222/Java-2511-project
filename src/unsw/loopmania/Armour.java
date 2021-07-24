package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Armour in the backend world
 */
public class Armour extends BasicItem {
    private final int price = 1000;
    // TODO = add more weapon/item types
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.ARMOUR);
    }
    public int getDefense(EnemyProperty enemy) {
        return enemy.getDamage()/2;
    }
    public int getPrice() {
        return this.price;
    }

}