package unsw.loopmania;

import java.util.List;

public abstract class EnemyProperty extends BasicEnemy{
    public EnemyProperty(PathPosition position) {
        super(position);
        //TODO Auto-generated constructor stub
    }

    public abstract boolean isSlug();

    abstract public void attack(LoopManiaWorld l, List<Ally> defeatedAllies, List<EnemyProperty> transferZombies, boolean inBattle, ItemProperty[] equipments);
}
