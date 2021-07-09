package unsw.loopmania;

import java.util.AbstractMap.SimpleImmutableEntry;

import javafx.beans.property.SimpleIntegerProperty;

public class Barracks extends Building{
    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public Ally produceAlly() {
        if (getCharacterStepOn()) {
            //TODO: create a new class of ally
            // and put it in the global data
        }
        return null;
    }

    public boolean checkPathType() {
        // Get the Pathtype at (x, y)
        // If it is pathtiles and no other buildings exsits on it
        // place the village
    }
}
