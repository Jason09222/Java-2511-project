package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePit extends Building {
    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public boolean checkPathCycle() {
        if (this.getPathCycle() % 5 == 0) {
            return true;
        }
        return false;
    }

    public void spawnZombie(LoopManiaWorld l) {
        // create a new class of zombie and put it in the global data
        PathPosition position = getNearestPath(l);
        BasicEnemy newZom = new Zombie(position);
        l.getEnemy().add(newZom);
    }
}
