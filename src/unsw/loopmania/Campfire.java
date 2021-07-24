package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Campfire extends Building {
    private final int campRadius = 3; // TODO; the value may change later;

    public Campfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public int getcampRadius() {
        return this.campRadius;
    }
    

    public void doubleDamage(Character c) {
        // get the character's damage and double it
        // deduct corresponding points from the enemy
        for (EnemyProperty enemies : super.getEnemies()) {
            enemies.setHP(enemies.getHP() - 2 * c.getDamage());
        }
    }


}
