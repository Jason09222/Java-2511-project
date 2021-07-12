package unsw.loopmania;

import java.util.Random;
import java.lang.Math;
/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity {
    // TODO = modify this, and add additional forms of enemy


    private String type;
    private int hp;
    private int exp;
    private int damage;
    private int fightRadius;
    private int supportRadius;
    //private boolean critical;
    private boolean weak;
    private int gold;
    private int speed;
    private boolean inBattle;


    public BasicEnemy(PathPosition position) {
        super(position);
        this.inBattle = false;
    }


    public String getType() {
        return this.type;
    }
    
    public int getHP() {
        return this.hp;
    }

    public int getExp(){
        return this.exp;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getFightRadius() {
        return this.fightRadius;
    }

    public int getSupportRadius() {
        return this.supportRadius;
    }

    public boolean getIsWeak() {
        return this.weak;
    }

    public int getGold() {
        return this.gold;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Boolean getInBattle() {
        return this.inBattle;
    }


    public void generateTrophy() {
    
        Random rand = new Random();
        int int_random = rand.nextInt(3);
        switch(int_random) {
            case 0:
                //TODO set some amount of gold to map
                break;
            case 1:
                //TODO: set some kind of weapon to map
                break;
            case 2:
                //TODO: set one health potion to map
                break;
            case 3:
                //TODO: set one card to map
                break;
            default:
                return;
        }
    }
    
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setHP(int hp) {
        // TODO: set the limit of hp
        this.hp = hp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setFightRadius(int radius) {
        this.fightRadius = radius;
    }

    public void setSupportRadius(int radius) {
        this.supportRadius = radius;
    }

    public void setIsWeak(boolean flag) {
        this.weak = flag;
    }
    
    public void setGold(int g) {
        this.gold = g;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setInBattle(boolean flag) {
        this.inBattle = flag;
    }

    public void attack() {
        //TODO
        return;
    }


    /**
     * move the enemy
     */
    public void move(){
        // TODO = modify this, since this implementation doesn't provide the expected enemy behaviour
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }


    public int getDistance(PathPosition dest) {
        int destX = dest.getX().get();
        int destY = dest.getY().get();
        int srcX = this.getX();
        int srcY = this.getY();
        return (int)Math.sqrt(Math.pow(destX - srcX,2) + Math.pow(destY - srcY , 2));
    }
}
