package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class RareItem extends StaticEntity  {
    String type;
    public RareItem(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        super(x, y);
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
}
