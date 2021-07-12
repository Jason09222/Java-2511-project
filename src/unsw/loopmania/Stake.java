package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Stake in the backend world
 */
public class Stake extends BasicItem {
    private final int damage = 150;
    private final int price = 1500;
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, "Stake");
    }
    public int getDamage(BasicEnemy enemy) {
        if (enemy.getType().equals("Vampire")) {
            return 2 * damage;
        } else {
            return damage;
        }

    }
    public int getPrice() {
        return price;
    }
}