package unsw.loopmania;

public class Ally extends MovingEntity{
    private String type = "Ally";
    private int hp = 300;
    private int damage = 100;
    private int round = 0;
    private String originalType = null;

    public Ally(PathPosition position) {
        super(position);
    }

    public String getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }

    public void attack(BasicEnemy e) {
        e.setHP(e.getHP() - damage);
    }

}
