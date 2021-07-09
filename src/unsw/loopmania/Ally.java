package unsw.loopmania;

public class Ally extends MovingEntity{
    private String id;
    private int hp;
    private int damage;

    public Ally(String id, int hp, int damage, PathPosition position) {
        super(position);
        this.id = id;
        this.hp = hp;
        this.damage = damage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
}
