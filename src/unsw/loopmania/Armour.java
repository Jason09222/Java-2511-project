package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Armour in the backend world
 */
public class Armour extends ItemProperty {
    private final int price = 1000;
    // TODO = add more weapon/item types
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.ARMOUR);
    }
    public int getDefense(EnemyProperty enemy) {
        return enemy.getDamage()/2;
    }
    public int getPrice() {
        return this.price;
    }
    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        e.setDamage(e.getDamage()/ 2);
        
    }
    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemoveGold,
            List<ItemProperty> toRemoveHealthPotion) {
        // TODO Auto-generated method stub
        return;
    }
    
    

}