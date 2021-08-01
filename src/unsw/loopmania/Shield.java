package unsw.loopmania;

import java.io.File;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * represents an equipped or unequipped Shield in the backend world
 */
public class Shield extends ItemProperty {
    // TODO = add more weapon/item types
    private final int defense = 10;
    public static IntegerProperty price = new SimpleIntegerProperty(2000);
    private Image shieldImage;
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.SHIELD);
        shieldImage = new Image((new File("src/images/shield.png")).toURI().toString());
    }
    public int getDefense() {
        return this.defense;
    }
    
    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        // TODO Auto-generated method stub
        if (e.getDamage() < defense) {
            e.setDamage(0);
        } else {
            e.setDamage(e.getDamage() - defense);
        }
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

    @Override
    public ImageView onLoadItems() {
        return new ImageView(shieldImage);
    }
    @Override
    public boolean canBePurchased() {
        // TODO Auto-generated method stub
        return true;
    }
}