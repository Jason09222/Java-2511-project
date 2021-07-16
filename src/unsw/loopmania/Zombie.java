package unsw.loopmania;
import java.util.Random;
public class Zombie extends BasicEnemy{
    //TODO can be changed
    private final String type = "Zombie";
    private final int FightR = 2;
    private final int SupportR = 5;
    private final int gold = 300;
    private final int speed = 2;
    private final int damage = 100;
    //private final boolean cirtical = true;
    private final boolean weak = false;
    private final int hp = 300;
    private final int exp = 5;
    public Zombie(PathPosition position) {
        super(position);
        setType(this.type);
        setHP(this.hp);
        setExp(this.exp);
        setDamage(this.damage);
        setFightRadius(this.FightR);
        setSupportRadius(this.SupportR);
        //setIsCritical(this.cirtical);
        setIsWeak(this.weak);
        setGold(this.gold); //TODO can be changed
        setSpeed(this.speed);
    }

    @Override
    //TODO add character object as parameter
    public void attack_ally(Ally ally) {
        Random rand = new Random();
        int int_random = rand.nextInt(5);
        if (int_random == 0) {
            //TODO set ally to zombie

            return;
        }
        //TODO deduct hp of ally/Character

        ally.setHp(ally.getHp() - this.getDamage());
        return;
    }



  


}
