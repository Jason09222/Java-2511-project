package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped HealthPotion in the backend world
 */
public class HealthPotion extends ItemProperty {
    private final int price = 2000;
    private final int health = 200;
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.HEALTHPOTION);
    }
    public int getPrice() {
        return price;
    }
    public int getHealth() {
        return health;
    }
    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        return;
        
    }
    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemoveGold, List<ItemProperty> toRemoveHealthPotion) {
        if (l.getCharacter().getX() == getX() && l.getCharacter().getY() == getY()) {
            toRemoveHealthPotion.add(this);
        }
        
    }
}