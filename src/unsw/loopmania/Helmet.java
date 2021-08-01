package unsw.loopmania;

import java.io.File;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * represents an equipped or unequipped Helmet in the backend world
 */
public class Helmet extends ItemProperty {
    private final int defense = 30;
    public static IntegerProperty price = new SimpleIntegerProperty(1500);
    private Image helmetImage;
    // TODO = add more weapon/item types
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.HELMET);
        helmetImage = new Image((new File("src/images/helmet.png")).toURI().toString());
    }
    public int getDefense() {
        return this.defense;
    }
    

    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        // TODO Auto-generated method stub
        if (e.getDamage() > getDefense()) {
            e.setDamage(e.getDamage() - getDefense());
        } else {
            e.setDamage(0);
        }
        
    }
    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemoveGold,
            List<ItemProperty> toRemoveHealthPotion) {
        // TODO Auto-generated method stub
        return;
    }
    
    @Override
    public ImageView onLoadItems() {
        return new ImageView(helmetImage);
    }
    @Override
    public boolean canBePurchased() {
        // TODO Auto-generated method stub
        return true;
    }
}