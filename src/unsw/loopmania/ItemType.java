package unsw.loopmania;

public enum ItemType {
    STAKE(0),
    STAFF(0),
    SWORD(0),
    ARMOUR(3),
    HELMET(1),
    SHIELD(2),
    OTHER(4),
    HEALTHPOTION(4),
    DOGGIECOIN(5),    
    ;

    private int index;
    
    ItemType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}

