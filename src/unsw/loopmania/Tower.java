package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Tower extends Building {
    private final int shootRadius = 5; // TODO: this value may be changed later

    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public boolean checkPathType() {
        // Get the Pathtype at (x, y)
        // If it is non-pathtiles and no other buildings exsits on it
        // place the village
    } 

    @Override
    public void addEnemy() {
        // loop through all enemies
        // calculate the distance between tower and enemy
        // if it is in radius
        // add to the list
    }

    public boolean checkBattle() {
        // loop through all battles happening now 
        // loop through the enemies in the battle
        // decuct hp
    }
}
