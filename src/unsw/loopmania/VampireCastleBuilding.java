package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building {

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public boolean checkPathCycle(LoopManiaWorld l) {
        if (this.getPathCycle() % (l.getOrderedPath().size()) == 0) {
            return true;
        }
        return false;
    }

    public Vampire spawnVampire(LoopManiaWorld l) {
        // create a new class of vampire and put it in the global data
        PathPosition position = getNearestPath(l);
        Vampire newVam = new Vampire(position);
        l.getEnemy().add(newVam);
        return newVam;
    }
}
