package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BasicItem extends StaticEntity {
    String type;
    public BasicItem(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        super(x, y);
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
}
