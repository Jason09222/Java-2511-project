package unsw.loopmania;

import java.io.File;
import java.util.List;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DoggieCoin extends ItemProperty implements Observer{
    public static IntegerProperty price;

    BooleanProperty muskeSpawned;
    BooleanProperty muskeAlive;
    private Image doggieImage = new Image((new File("src/images/doggiecoin.png")).toURI().toString()); 

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y, ItemType type) {
        super(x, y, type);
        price = new SimpleIntegerProperty(800);
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
        return new ImageView(doggieImage);
    }

    @Override
    public boolean canBePurchased() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void update(LoopManiaWorld l) {
        // TODO Auto-generated method stub
        Random rand = new Random();
        if (!l.hasSpawnMuske.get()) {
            int result = rand.nextInt(2);
            if (result == 0) {
                price.set(price.get() + rand.nextInt(30));
            } else {
                price.set(price.get() - rand.nextInt(30));
            }
        } else {
            if (!l.hasKilledMuske.get()) {
                price.set(price.get() + rand.nextInt(50));
            } else {
                price.set(300);
                l.hasSpawnMuske.set(false);
            }
        }
        
    }
    
}
