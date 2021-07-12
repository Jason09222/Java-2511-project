package unsw.loopmania;
import java.util.ArrayList;
import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    // TODO = potentially implement relationships between this class and other classes
    private int hp = 500;
    private int damage = 100;
    private List<Entity> equippedInventoryItems;
    private Boolean inBattle;

    public Character(PathPosition position) {
        super(position);
        setHp(this.hp);
        setDamage(this.damage);
        this.equippedInventoryItems = new ArrayList<Entity>();
        this.inBattle = false;
    }
    
    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        // TODO: Check if it reaches the highest possible hp
        this.hp = hp;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public List<Entity> getEquippedInventoryItems() {
        return this.equippedInventoryItems;
    }

    public void setEquippedInventoryItems(List<Entity> equippedItems) {
        this.equippedInventoryItems = equippedItems;
    }

    public void equipItem(Entity equipment) {
        this.equippedInventoryItems.add(equipment);
    }

    public void setInBattle(Boolean flag) {
        this.inBattle = flag;
    }

    public Boolean getInBattle() {
        return this.inBattle;
    }

    public void attack(BasicEnemy e) {
        //TODO
        e.setHP(e.getHP() - this.getDamage());
    }

}


