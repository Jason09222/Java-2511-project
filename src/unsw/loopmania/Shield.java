package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Shield in the backend world
 */
public class Shield extends ItemProperty {
    // TODO = add more weapon/item types
    private final int defense = 10;
    private final int price = 2000;
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.SHIELD);
    }
    public int getDefense() {
        return this.defense;
    }
    public int getPrice() {
        return this.price;
    }
    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        // TODO Auto-generated method stub
        e.setDamage(e.getDamage() - defense);
        if (e.getType().equals("Vampire")) {
            e.setCriticalPoss((int)(e.getCriticalPoss() / 0.4));
        }
        
    }
   
    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemoveGold,
            List<ItemProperty> toRemoveHealthPotion) {
        // TODO Auto-generated method stub
            return;
    }
}