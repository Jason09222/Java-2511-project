package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Stake in the backend world
 */
public class Stake extends ItemProperty {
    private final int damage = 150;
    private final int price = 1500;
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.STAKE);
    }
    public int getDamage(EnemyProperty enemy) {
        if (enemy.getType().equals("Vampire")) {
            return 2 * damage;
        } else {
            return damage;
        }

    }
    public int getPrice() {
        return price;
    }
    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        // TODO Auto-generated method stub
        if (e.getType().equals("Vampire")) {
            c.setDamage(c.getDamage() + 2 * damage);
        } else {
            c.setDamage(c.getDamage() + damage);
        }
        
    }

    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemoveGold,
            List<ItemProperty> toRemoveHealthPotion) {
        // TODO Auto-generated method stub
        
    }
}