package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class ItemProperty extends BasicItem{

    public ItemProperty(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemType type) {
        super(x, y, type);
        //TODO Auto-generated constructor stub
    }

    abstract public void useDuringBattle(EnemyProperty e, Character c);
    abstract public void characterStepOn(LoopManiaWorld l,List<ItemProperty> toRemove);
    
}
