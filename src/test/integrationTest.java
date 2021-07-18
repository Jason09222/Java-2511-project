package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Card;

import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Ally;
import unsw.loopmania.Armour;
import unsw.loopmania.Barracks;
import unsw.loopmania.BarracksCard;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.BasicItem;
import unsw.loopmania.Campfire;
import unsw.loopmania.CampfireCard;
import unsw.loopmania.Character;
import unsw.loopmania.Goals;
import unsw.loopmania.Gold;
import unsw.loopmania.HealthPotion;
import unsw.loopmania.Helmet;
import unsw.loopmania.HeroCastle;
import unsw.loopmania.ItemType;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldController;
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
        Ally ally = new Ally(position);
        Character character = new Character(position);
        //
        Vampire vampire = new Vampire(position);
        Zombie zombie = new Zombie(position);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        // Armour armour = new Armour(x, y);
        // Barracks barrack = new Barracks(x, y);
        // BarracksCard barracksCard = new BarracksCard("Barracks", x, y);
        // Campfire campfire = new Campfire(x, y);
        // CampfireCard campfireCard = new CampfireCard("Campfire", x, y);
        // Gold gold = new Gold(x, y);
        // HealthPotion healthPotion = new HealthPotion(x, y);
        // Helmet helmet = new Helmet(x, y);
        // HeroCastle heroCastle = new HeroCastle(x, y);
        // Shield shield = new Shield(x, y);
        Slug slug = new Slug(position);
        // Staff staff = new Staff(x, y);
        // Stake stake = new Stake(x, y);
        // Sword sword = new Sword(x, y);
        // TheOneRing theOneRing = new TheOneRing(x, y);
        // Tower tower = new Tower(x, y);
        // TowerCard towerCard = new TowerCard("Tower", x, y);
        // Trap trap = new Trap(x, y);
        // TrapCard trapCard = new TrapCard("Trap", x, y);
        // VampireCastleBuilding vampireCastleBuilding = new VampireCastleBuilding(x, y);
        // VampireCastleCard vampireCastleCard = new VampireCastleCard("VampireCastleBuilding", x, y);
        // Village village = new Village(x, y);
        // VillageCard villageCard = new VillageCard("Village", x, y);
        // ZombiePit zombiePit = new ZombiePit(x, y);
        // ZombiePitCard zombiePitCard = new ZombiePitCard("ZombiePit", x, y);
        // for (int i = 0; i < 20 * orderedPath.size(); i++) {
        //     // TODO = handle more aspects of the behaviour required by the specification
        //     System.out.println("starting timer");

        //     // trigger adding code to process main game logic to queue. JavaFX will target
        //     // framerate of 0.3 seconds

        //     world.runTickMoves();
        //     List<BasicItem> items = world.possiblySpawnItems();
        //     List<BasicEnemy> defeatedEnemies = world.runBattles();
        //     List<BasicEnemy> newEnemies = world.possiblySpawnEnemies();

        // }
        assertEquals(0, world.getAllies().size());
        assertEquals(0, world.getEnemy().size());
        assertEquals(0, world.getCampfire().size());
        assertNotEquals(null, world.getOrderedPath());
        assertEquals(0, world.getUnpickedItems().size());
        world.addAlly(ally);
        assertEquals(1, world.getAllies().size());
        assertEquals(5, world.getWidth());
        assertEquals(5, world.getHeight());
        world.setCharacter(character);
        world.addEntity(character);
        world.getEnemy().add(slug);

        assertNotEquals(2, world.possiblySpawnEnemies().size());
        world.getEnemy().add(vampire);
        world.getEnemy().add(zombie);
        
        assertEquals(2, world.possiblySpawnItems().size());
        world.killEnemy(vampire);
        //world.killEnemy(zombie);


        List<BasicEnemy> enemies = new ArrayList<>();
        while (true) {
            if (world.getEnemy().size() == 0)
                break;
            if (character.getHp() <= 0) {
                break;
            }
            enemies.addAll(world.runBattles());
        }
        assertNotEquals(0, enemies.size());
        world.killAlly(ally);
        assertEquals(0, world.getAllies().size());
        assertNotEquals(null, world.generateItem());
        world.addUnequippedSword();
        assertEquals(ItemType.SWORD, world.addUnequippedItem(ItemType.SWORD).getType());
        world.unEquipItem(0);
        world.equipItemByCoordinates(0, 0);
        world.removeUnequippedInventoryItemByCoordinates(1, 0);
        for (int i = 0; i < orderedPath.size(); i++) {
            world.runTickMoves();
        }
        assertNotEquals(0, world.getGold());
        assertEquals(1, world.getPotions());
        character.setHp(100);
        world.spendPotions();
        assertEquals(500, character.getHp());
        assertEquals(0, world.getPotions());
        assertNotEquals(0, world.getGold());
        //DoubleProperty gold = world.getGold();
        world.addGold(100);
        //assertEquals(gold + 100, world.getGold());
        world.spendGold(11);
        //assertEquals(gold, world.getGold());
        assertNotEquals(0, world.getExperience());
        world.addExperience(100);
        BasicItem sword = new Sword(x, y);
        world.addUnequippedInventory(sword);
        BasicEnemy slugb = new Slug(position);
        //world.generateTrophy(slugb);
        assertEquals(0, world.getBuildings().size());
        world.createbuilding("VampireCastle", x, y);
        //System.err.println(world.getBuildings());
        //assertEquals(1, world.getBuildings().size());
        world.loadVampireCard();
        world.loadCampfireCard();
        world.loadTowerCard();
        world.loadTrapCard();
        world.loadBarracksCard();
        world.loadVillageCard();
        world.loadZombiePitCard();
        assertNotEquals(null, world.generateCard());
        Card card = world.generateCard();
        world.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 2, 3);
        Goals goal = new Goals();
        goal.goalComplete(20, 20, 1);
        world.getHealthPotionNum();
        world.getExpInt();
        world.addExperience(3);
        world.getHpInt();
        world.getAllyNum();
        world.isGameOver();
        world.getExp();
        world.getHp();
        world.getCylceNum();
        world.isGameWin();
        world.isShopTime();
        world.updatePathCycle();

        world.getShortestCampfire(slugb);
        world.checkAdjacentToPathTile(x, y);
        world.supportMove(slugb);
        world.getAwayFromCampfire(slugb);
        world.createbuilding("Trap", x, y);
        world.charactersStepOnBuilding();
        world.moveBasicEnemies();
        world.createbuilding("Village", x, y);
        world.createbuilding("Barracks", x, y);
        world.createbuilding("Tower", x, y);
        world.createbuilding("VampireCastleBuilding", x, y);
        world.createbuilding("ZombiePit", x, y);
        world.createbuilding("Campfire", x, y);
        assertEquals(true, world.checkBuildingAlrdyExisted(x, y));
        world.enemyStepOnBuilding();
        Ally ally1 = new Ally(position);
        Ally ally2 = new Ally(position);
        Ally ally3 = new Ally(position);
        world.addAlly(ally1);
        world.addAlly(ally2);
        world.addAlly(ally3);
        world.runTickMoves();
        for (int i = 0; i < 40; i++) {
            world.generateCard();
        }
        for (int i = 0; i < 40; i++) {
            world.generateItem();
        }




    }
}
