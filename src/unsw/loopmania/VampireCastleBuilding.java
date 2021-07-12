package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building {

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public boolean checkPathCycle() {
        if (this.getPathCycle() % 5 == 0) {
            return true;
        }
        return false;
    }

    public void spawnVampire(LoopManiaWorld l) {
        // create a new class of vampire and put it in the global data
        PathPosition position = getNearestPath(l);
        BasicEnemy newVam = new Vampire(position);
        l.getEnemy().add(newVam);
    }
}
