package unsw.loopmania;


import java.io.File;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Barracks extends BuildingProperty{
    private Image barracksImage;
    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        barracksImage = new Image((new File("src/images/barracks.png")).toURI().toString());
    }

    public void produceAlly(LoopManiaWorld l) {
        //TODO: create a new class of ally
        // and put it in the global data
        PathPosition position = super.getNearestPath(l);
        Ally newAlly = new Ally(position);
        //l.getAllies().add(newAlly);
        l.addAlly(newAlly);;
    }

    @Override
    public void spawnEnemy(LoopManiaWorld l, List<EnemyProperty> spawningEnemies) {
        return;
    }

    @Override
    public void characterStepOn(LoopManiaWorld l) {
        produceAlly(l);
    }

    @Override
    public void enemyStepOn(LoopManiaWorld l, List<BuildingProperty> toRemove) {
        return;
    }

    @Override
    public ImageView onLoadBuilding() {
        // TODO Auto-generated method stub
        return new ImageView(barracksImage);
    }
    
}
