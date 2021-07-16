package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class RareItem extends Item  {
    ItemType type;
    public RareItem(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemType type) {
        super(x, y, type);
        this.type = type;

}
