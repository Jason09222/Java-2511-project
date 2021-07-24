package unsw.loopmania;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Staff in the backend world
 */
public class Staff extends BasicItem {
    private final int damage = 100;
    private final int price = 2000;

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.STAFF);
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

    public void trance(EnemyProperty enemy, LoopManiaWorld world) {
        Random rand = new Random();
        int random = rand.nextInt(5);
        if (random == 0) {
            PathPosition position = enemy.getPathPosition();
            world.killEnemy(enemy);
            Ally ally = new Ally(position);
            ally.setRound(5);
            ally.setOriginalType(enemy.getType());
            world.addAlly(ally);
        }
    }
}