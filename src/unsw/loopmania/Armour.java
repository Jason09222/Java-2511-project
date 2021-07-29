package unsw.loopmania;

import java.io.File;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * represents an equipped or unequipped Armour in the backend world
 */
public class Armour extends ItemProperty {
    private final int price = 1000;
    private Image armourImage;
    // TODO = add more weapon/item types
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.ARMOUR);
        armourImage = new Image((new File("src/images/armour.png")).toURI().toString());
    }
    public int getDefense(EnemyProperty enemy) {
        return enemy.getDamage()/2;
    }
    @Override
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
    
    @Override
    public ImageView onLoadItems() {
        return new ImageView(armourImage);
    }
    @Override
    public boolean canBePurchased() {
        // TODO Auto-generated method stub
        return true;
    }
    

}