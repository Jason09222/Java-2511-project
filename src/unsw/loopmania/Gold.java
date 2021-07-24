package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Gold in the backend world
 */
public class Gold extends ItemProperty {
    private final int value = 200;
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.OTHER);
    }
    public int getValue() {
        return value;
    }
    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        return;
        
    }
    @Override
    public void characterStepOn(LoopManiaWorld l,List<ItemProperty> toRemoveGold, List<ItemProperty> toRemoveHealthPotion) {
        // TODO Auto-generated method stub
        if (l.getCharacter().getX() == getX() && l.getCharacter().getY() == getY()) {
            toRemoveGold.add(this);
        }
        
    }
}