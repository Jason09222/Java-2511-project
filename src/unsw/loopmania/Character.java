package unsw.loopmania;
// import java.util.ArrayList;
// import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    // TODO = potentially implement relationships between this class and other classes
    private int hp = 500;
    private int damage = 100;
    private Boolean inBattle;

    public Character(PathPosition position) {
        super(position);
        setHp(this.hp);
        setDamage(this.damage);
        this.inBattle = false;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        // TODO: Check if it reaches the highest possible hp
        if (hp < 0) this.hp = 0;
        else if (hp > 500) this.hp = 500;
        else this.hp = hp;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }



    public void setInBattle(Boolean flag) {
        this.inBattle = flag;
    }

    public Boolean getInBattle() {
        return this.inBattle;
    }



    //TODO : check equipped item and use weapon
    public void attack(BasicEnemy e) {
        //TODO
        e.setHP(e.getHP() - this.getDamage());
    }

    public void useHealthPotion(HealthPotion healthPotion) {
        hp += healthPotion.getHealth();
        if (hp >= 500) {
            hp = 500;
        }
    }  

    public void useSword(Sword sword, BasicEnemy enemy) {
        enemy.setHP(enemy.getHP() - damage - sword.getDamage());
    }

    public void useStake(Stake stake, BasicEnemy enemy) {
        enemy.setHP(enemy.getHP() - damage - stake.getDamage(enemy));
    }

    public void useStaff(Staff staff, BasicEnemy enemy, LoopManiaWorld world) {
        enemy.setHP(enemy.getHP() - damage - staff.getDamage());
        staff.trance(enemy, world);
    }

    public void useTheOneRing(TheOneRing theOneRing) {
        hp = 500;
    }
}


