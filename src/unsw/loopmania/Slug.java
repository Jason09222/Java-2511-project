package unsw.loopmania;

import java.util.List;

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
    private final int criticalPoss = 0;
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
        setCriticalPoss(criticalPoss);

    }

    @Override 
    public boolean isSlug() {
        return true;
    }

    @Override
    public void setAllPropertyBack() {
        setDamage(20);
        setCriticalPoss(0);
    }

    @Override
    public void attack(LoopManiaWorld l, List<Ally> defeatedAllies, List<EnemyProperty> transferZombies, boolean inBattle, ItemProperty[] equipments) {
        // TODO Auto-generated method stub

        if (Math.pow((l.getCharacter().getX() - getX()), 2) + Math.pow((l.getCharacter().getY() - getY()), 2) > Math
                    .pow(getFightRadius(), 2)) {
            return;
                    }

        boolean hasAttacked = false;
        for (Ally ally : l.getAllies()) {
            if (ally.getHp() <= 0) {
                continue;
            }
            
            l.getCharacter().setInBattle(true);
            inBattle = true;
            //e.attack_ally(ally);
            hasAttacked = true;
            //if (ally.getHp() <= 0) {
            
            ally.setHp(ally.getHp() - getDamage());
            if (ally.getHp() <= 0) {
                defeatedAllies.add(ally);
            }
            break;
        }
        if (!hasAttacked) {
            l.getCharacter().setInBattle(true);
            inBattle = true;
            //for (ItemProperty item : l)
            for (ItemProperty item : equipments) {
                if (item == null) {
                    continue;
                } 
                item.useDuringBattle(this, l.getCharacter());
            }
            attack_character(l.getCharacter());
        }
        
    }

    

}


