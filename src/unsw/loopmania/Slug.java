package unsw.loopmania;
//import java.util.Random;
public class Slug extends EnemyProperty{
    //TODO can be changed
    private final String type = "Slug";
    private final int FightR = 1;
    private final int SupportR = 1;
    private final int gold = 100;
    private final int speed = 2;
    private final int damage = 20;
    //private final boolean cirtical = true;
    private final boolean weak = false;
    private final int hp = 300;
    private final int exp = 100;
    public Slug(PathPosition position) {
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
    public boolean isSlug() {
        return true;
    }



}


