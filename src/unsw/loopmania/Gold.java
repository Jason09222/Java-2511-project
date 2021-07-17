package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Gold in the backend world
 */
public class Gold extends BasicItem {
    private final int value = 200;
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.OTHER);
    }
    public int getValue() {
        return value;
    }
}