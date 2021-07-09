package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Village extends Building{
    private final int hpGain = 4; // TODO: the value might be changed

    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public boolean checkPathType() {
        // Get the Pathtype at (x, y)
        // If it is pathtiles and no other buildings exsits on it
        // place the village
    }

    public int getHpGain() {
        return this.hpGain;
    }

    public void increaseHp() {
        // TODO: Add on the character's hp
    }
}
