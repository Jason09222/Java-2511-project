package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped TheOneRing in the backend world
 */
public class TheOneRing extends RareItem {
    // TODO = add more weapon/item types
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "TheOneRing");
    }
}