package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public class Trap extends Building {
    private final int damage = 4; // TODO: this value can be changed later

    public Trap (SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public void destroyTrap(LoopManiaWorld l) {
        //fetch from the global buildings and remove this object
        for (Building b : l.getBuildings()) {
            if (b.equals(this)) {
                l.getBuildings().remove(b);
                b.destroy();
            }
        }
    }

    public int getDamage() {
        return this.damage;
    }

    public void exertDamage(LoopManiaWorld l, List<Building> toRemove) {
        // TODO: Deduct corresponding hp from the enemy
        super.addEnemiesWorld(l);
        for (BasicEnemy enemy : super.getEnemies()) {
            enemy.setHP(enemy.getHP() - this.damage);
        }
        if (super.getEnemies().size() > 0) {
            toRemove.add(this);
        }
        //destroyTrap(l);
        //l.getBuildings().remove(this);
        //this.destroy();
    }
}
