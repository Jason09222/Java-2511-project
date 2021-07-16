package unsw.loopmania;

public enum ItemType {
    WEAPON(0),
    SWORD(0),
    STAKE(0),
    STAFF(0),
    ARMOUR(1),
    HELMET(2),
    SHIELD(3),
    OTHER(4),
    HEALTHPOTION(4),    
    ;

    private int index;
    
    ItemType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}

