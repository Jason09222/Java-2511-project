package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped HealthPotion in the backend world
 */
public class HealthPotion extends BasicItem {
    // TODO = add more weapon/item types
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "HealthPotion");
    }
}