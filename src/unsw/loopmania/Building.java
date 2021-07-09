package unsw.loopmania;


import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;

abstract public class Building extends StaticEntity {
    private int pathCycle = 0;
    private Boolean characterStepOn = false;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Building (SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public int getPathCycle() {
        return this.pathCycle;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public Boolean getCharacterStepOn() {
        return this.characterStepOn;
    }

    public void setPathCycle(int newPathCycle) {
        this.pathCycle = newPathCycle;
    }

    public abstract boolean checkPathType(); // TODO: check if the tile type is allowed to be place a building

    //public abstract boolean checkPathCycle();

    public void addEnemy(Enemy stepOn) {
        this.enemies.add(stepOn);
    }

    public void removeEnemy(Enemy stepOn) {
        this.enemies.remove(stepOn);
    }

    public void characterSteppingOn() {
        this.characterStepOn = true;
    }

    public void characterLeave() {
        this.characterStepOn = false;
    }
}
