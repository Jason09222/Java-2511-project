package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePit extends Building {
    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public boolean checkPathType() {
        // Get the Pathtype at (x, y)
        // If it is non-pathtiles and no other buildings exsits on it
        // place the village
    }

    public void spawnZombie() {
        // create a new class of zombie and put it in the global data
    }
}
