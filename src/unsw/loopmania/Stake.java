package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Stake in the backend world
 */
public class Stake extends BasicItem {
    // TODO = add more weapon/item types
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "Stake");
    }
}