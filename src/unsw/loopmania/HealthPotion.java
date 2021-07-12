package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped HealthPotion in the backend world
 */
public class HealthPotion extends BasicItem {
    private final int price = 5000;
    private final int health = 200;
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "HealthPotion");
    }
    public int getPrice() {
        return price;
    }
    public int getHealth() {
        return health;
    }
}