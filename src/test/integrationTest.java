package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Ally;
import unsw.loopmania.Armour;
import unsw.loopmania.Barracks;
import unsw.loopmania.BarracksCard;
import unsw.loopmania.Campfire;
import unsw.loopmania.CampfireCard;
import unsw.loopmania.Character;
import unsw.loopmania.Gold;
import unsw.loopmania.HealthPotion;
import unsw.loopmania.Helmet;
import unsw.loopmania.HeroCastle;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Shield;
import unsw.loopmania.Slug;
import unsw.loopmania.Staff;
import unsw.loopmania.Stake;
import unsw.loopmania.Sword;
import unsw.loopmania.TheOneRing;
import unsw.loopmania.Tower;
import unsw.loopmania.TowerCard;
import unsw.loopmania.Trap;
import unsw.loopmania.TrapCard;
import unsw.loopmania.Vampire;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.VampireCastleCard;
import unsw.loopmania.Village;
import unsw.loopmania.VillageCard;
import unsw.loopmania.Zombie;
import unsw.loopmania.ZombiePit;
import unsw.loopmania.ZombiePitCard;

import org.javatuples.Pair;

public class integrationTest {
    @Test
    public void Test() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Character character = new Character(position);
        Ally ally = new Ally(position);
        Vampire vampire = new Vampire(position);
        Zombie zombie = new Zombie(position);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Armour armour = new Armour(x, y);
        Barracks barrack = new Barracks(x, y);
        BarracksCard barracksCard = new BarracksCard("Barracks", x, y);
        Campfire campfire = new Campfire(x, y);
        CampfireCard campfireCard = new CampfireCard("Campfire", x, y);
        Gold gold = new Gold(x, y);
        HealthPotion healthPotion = new HealthPotion(x, y);
        Helmet helmet = new Helmet(x, y);
        HeroCastle heroCastle = new HeroCastle(x, y);
        Shield shield = new Shield(x, y);
        Slug slug = new Slug(position);
        Staff staff = new Staff(x, y);
        Stake stake = new Stake(x, y);
        Sword sword = new Sword(x, y);
        TheOneRing theOneRing = new TheOneRing(x, y);
        Tower tower = new Tower(x, y);
        TowerCard towerCard = new TowerCard("Tower", x, y);
        Trap trap = new Trap(x, y);
        TrapCard trapCard = new TrapCard("Trap", x, y);
        VampireCastleBuilding vampireCastleBuilding = new VampireCastleBuilding(x, y);
        VampireCastleCard vampireCastleCard = new VampireCastleCard("VampireCastleBuilding", x, y);
        Village village = new Village(x, y);
        VillageCard villageCard = new VillageCard("Village", x, y);
        ZombiePit zombiePit = new ZombiePit(x, y);
        ZombiePitCard zombiePitCard = new ZombiePitCard("ZombiePit", x, y);
    }
}
