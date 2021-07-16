package unsw.loopmania;


import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;

abstract public class Building extends StaticEntity {
    private int pathCycle = 0;
    private Boolean characterStepOn = false;
    private ArrayList<BasicEnemy> enemies = new ArrayList<BasicEnemy>();

    public Building (SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public int getPathCycle() {
        return this.pathCycle;
    }

    public ArrayList<BasicEnemy> getEnemies() {
        return this.enemies;
    }

    public Boolean getCharacterStepOn() {
        return this.characterStepOn;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    public void setPathCycle(int newPathCycle) {
        this.pathCycle = newPathCycle;
    }


    //public abstract boolean checkPathCycle();

    public void addEnemy(BasicEnemy stepOn) {
        this.enemies.add(stepOn);
    }

    public void removeEnemy(BasicEnemy stepOn) {
        this.enemies.remove(stepOn);
    }

    public void characterSteppingOn() {
        this.characterStepOn = true;
    }

    public void characterLeave() {
        this.characterStepOn = false;
    }

    public void addEnemiesWorld(LoopManiaWorld l) {
        for (BasicEnemy enemy : l.getEnemy()) {
            if (enemy.getPathPosition().getX().get() == super.getX() && enemy.getPathPosition().getY().get() == super.getY()) {
                addEnemy(enemy);
            } else {
                for (BasicEnemy e : this.getEnemies()) {
                    if (e.equals(enemy)) removeEnemy(enemy);
                }
            }
        }
    }

    public PathPosition getNearestPath(LoopManiaWorld l) {
        PathPosition position = null;

        int i = 0;
        for (Pair<Integer, Integer> p : l.getOrderedPath()) {
            if (p.equals(new Pair<>(super.getX(), super.getY())) ||
            p.equals(new Pair<>(super.getX() - 1, super.getY())) ||
            p.equals(new Pair<>(super.getX() + 1, super.getY())) ||
            p.equals(new Pair<>(super.getX(), super.getY() + 1)) ||
            p.equals(new Pair<>(super.getX(), super.getY() - 1)) ||
            p.equals(new Pair<>(super.getX() - 1, super.getY() - 1)) ||
            p.equals(new Pair<>(super.getX() + 1, super.getY() + 1)) ||
            p.equals(new Pair<>(super.getX() - 1, super.getY() + 1)) ||
            p.equals(new Pair<>(super.getX() + 1, super.getY() - 1))) {
                
                position = new PathPosition(i, l.getOrderedPath());
                
            }

            
            i++;
        }
        return position; // non-pathtile if return value is null
    }
}
