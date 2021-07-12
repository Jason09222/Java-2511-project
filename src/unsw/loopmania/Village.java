package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Village extends Building{
    private final int hpGain = 4; // TODO: the value might be changed

    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public int getHpGain() {
        return this.hpGain;
    }

    public void increaseHp(Character c) {
        if (getCharacterStepOn()) {
            // Add on the character's hp
            c.setHp(c.getHp() + this.getHpGain());
        }
    }
}
