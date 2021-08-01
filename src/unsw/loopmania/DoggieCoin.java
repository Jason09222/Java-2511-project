package unsw.loopmania;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

public class DoggieCoin extends ItemProperty{
    public static IntegerProperty price = new SimpleIntegerProperty(200);
    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemType type) {
        super(x, y, type);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        return;
        
    }

    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemoveGold,
            List<ItemProperty> toRemoveHealthPotion) {
        return;
        
    }


    /*public void setPrice(int value) {
        this.price.set(value);
    }*/

    @Override
    public ImageView onLoadItems() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canBePurchased() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
