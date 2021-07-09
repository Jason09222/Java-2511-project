package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class VampireCastle extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public VampireCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public boolean checkPathType() {
        // Get the Pathtype at (x, y)
        // If it is non-pathtiles and no other buildings exsits on it
        // place the village
    }

    public boolean checkPathCycle() {
        if (this.getPathCycle() % 5 == 0) {
            return true;
        }
        return false;
    }

    public void spawnVampire() {
        // create a new class of zombie and put it in the global data
    }
}
