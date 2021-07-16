package unsw.loopmania;
import java.util.Random;
public class Vampire extends BasicEnemy{
    private final String type = "Vampire";
    private final int FightR = 2;
    private final int SupportR = 5;
    private final int gold = 500;
    private int speed = 2;
    private final int damage = 200;
    //private final boolean cirtical = true;
    private final boolean weak = false;
    private final int hp = 800;
    private final int exp = 5;

    private int criticalPoss;

    public Vampire(PathPosition position) {
        super(position);
        setType(type);
        setHP(this.hp);
        setExp(this.exp);
        setDamage(this.damage);
        setFightRadius(this.FightR);
        setSupportRadius(this.SupportR);
        //setIsCritical(this.cirtical);
        setIsWeak(this.weak);
        setGold(this.gold); //TODO can be changed
        setSpeed(this.speed);
        this.setCriticalPoss(10);
    }

    @Override
    //TODO add character object as parameter
    public void attack_ally(Ally ally) {
        Random rand = new Random();
        int int_random = rand.nextInt(5);
        if (int_random == 0) {
            int times = rand.nextInt(10);
            ally.setHp(ally.getHp() - this.getDamage() * times);
            //TODO random additional damage with every vampire attack, for a random number of vampire attacks
            return;
        }
        //TODO deduct hp of ally/Character
        ally.setHp(ally.getHp() - this.getDamage());
        return;
    }

    @Override
    public void attack_character(Character c) {
        Random rand = new Random();
        int int_random = rand.nextInt(getCriticalPoss());
        if (int_random == 0) {
            int times = rand.nextInt(10);
            c.setHp(c.getHp() - this.getDamage() * times);
            //TODO random additional damage with every vampire attack, for a random number of vampire attacks
            return;
        }
        //TODO deduct hp of ally/Character
        c.setHp(c.getHp() - this.getDamage());
        return;
    }

    public void setCriticalPoss(int value) {
        this.criticalPoss = value;
    }

    public int getCriticalPoss() {
        return this.criticalPoss;
    }

    public void setCriticalBack() {
        this.criticalPoss = 10;
    }


}

