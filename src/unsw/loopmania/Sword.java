package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends ItemProperty {
    private final int damage = 200;
    private final int price = 1000;
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.SWORD);
    }

    public int getDamage() {
        return this.damage;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        // TODO Auto-generated method stub
        c.setDamage(c.getDamage() + damage);
        
    }

    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemove) {
        // TODO Auto-generated method stub
        
    }
}
