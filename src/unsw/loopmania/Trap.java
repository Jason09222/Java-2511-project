package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Trap extends Building {
    private final int damage = 4; // TODO: this value can be changed later

    public Trap (SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public boolean checkPathType() {
        // Get the Pathtype at (x, y)
        // If it is pathtiles and no other buildings exsits on it
        // place the village
    }

    public void destroyTrap() {
        //fetch from the global buildings and remove this object
    }

    public int getDamage() {
        return this.damage;
    }

    public void exertDamage(Enemy stepOn) {
        // TODO: Deduct corresponding hp from the enemy
    }
}
