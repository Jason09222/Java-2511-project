package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Staff in the backend world
 */
public class Staff extends BasicItem {
    // TODO = add more weapon/item types
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "Staff");
    }
}