package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePit extends Building {
    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public boolean checkPathCycle(LoopManiaWorld l) {
        if (this.getPathCycle() % l.getOrderedPath().size() == 0) {
            return true;
        }
        return false;
    }

    public Zombie spawnZombie(LoopManiaWorld l) {
        // create a new class of zombie and put it in the global data
        PathPosition position = getNearestPath(l);
        Zombie newZom = new Zombie(position);
        l.getEnemy().add(newZom);
        return newZom;
    }
}
