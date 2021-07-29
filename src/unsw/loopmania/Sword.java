package unsw.loopmania;

import java.io.File;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends ItemProperty {
    private final int damage = 200;
    private final int price = 1000;
    private Image swordImage;
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, ItemType.SWORD);
        swordImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
    }

    public int getDamage() {
        return this.damage;
    }
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void useDuringBattle(EnemyProperty e, Character c) {
        // TODO Auto-generated method stub
        c.setDamage(c.getDamage() + damage);
        
    }



    @Override
    public void characterStepOn(LoopManiaWorld l, List<ItemProperty> toRemoveGold,
            List<ItemProperty> toRemoveHealthPotion) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ImageView onLoadItems() {
        return new ImageView(swordImage);
    }

    @Override
    public boolean canBePurchased() {
        // TODO Auto-generated method stub
        return true;
    }
}
