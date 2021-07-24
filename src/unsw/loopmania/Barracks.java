package unsw.loopmania;


import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public class Barracks extends BuildingProperty{
    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void produceAlly(LoopManiaWorld l) {
        //TODO: create a new class of ally
        // and put it in the global data
        PathPosition position = super.getNearestPath(l);
        Ally newAlly = new Ally(position);
        l.getAllies().add(newAlly);
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
}
