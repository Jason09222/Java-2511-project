package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public class Village extends BuildingProperty{
    private final int hpGain = 100; // TODO: the value might be changed

    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    public int getHpGain() {
        return this.hpGain;
    }

    public void increaseHp(Character c) {
       
        c.setHp(c.getHp() + this.getHpGain());
    }

    @Override
    public void spawnEnemy(LoopManiaWorld l, List<EnemyProperty> spawningEnemies) {
        return;
    }

    @Override
    public void characterStepOn(LoopManiaWorld l) {
        increaseHp(l.getCharacter());
    }

    @Override
    public void enemyStepOn(LoopManiaWorld l, List<BuildingProperty> toRemove) {
        return;
    }
}
