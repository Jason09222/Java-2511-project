package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Tower extends Building {
    private final int shootRadius = 5; // TODO: this value may be changed later
    private final int damage = 5;

    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public void addEnemyNearBy(LoopManiaWorld l) {
        // loop through all enemies
        // calculate the distance between tower and enemy
        // if it is in radius
        // add to the list
        super.getEnemies().clear();
        for (BasicEnemy enemy : l.getEnemy()) {
            double distance = getDistance(enemy.getX(), enemy.getY());
            if (distance <= this.shootRadius && enemy.getInBattle()) {
                super.addEnemy(enemy);
            } else {
                for (BasicEnemy e : l.getEnemy()) {
                    if (e.equals(enemy)) super.removeEnemy(e);
                }
            }
        }
        return;
    }

    public void decreaseHp() {
        for (BasicEnemy enemy : super.getEnemies()) {
            enemy.setHP(enemy.getHP() - this.damage);
        }
    }

    public void attack(LoopManiaWorld l) {
        addEnemyNearBy(l);
        decreaseHp();
    }


    public int getShootRadius() {
        return this.shootRadius;
    }

    public double getDistance(int destX, int destY) {
        int startX = super.getX();
        int startY = super.getY();
        return Math.sqrt(Math.pow(startX - destX, 2) - Math.pow(startY - destY, 2));
    }
}
