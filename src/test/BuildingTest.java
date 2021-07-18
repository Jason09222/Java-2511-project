package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.Tower;
import unsw.loopmania.Trap;
import unsw.loopmania.Vampire;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.Village;
import unsw.loopmania.ZombiePit;
import unsw.loopmania.Barracks;
import unsw.loopmania.Campfire;
import unsw.loopmania.Character;
import unsw.loopmania.HeroCastle;

public class BuildingTest {
    @Test
    public void testVampirecastle() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        VampireCastleBuilding vampireCastleBuilding = new VampireCastleBuilding(x, y);
        assertEquals(vampireCastleBuilding.getX(), 1);
        assertEquals(vampireCastleBuilding.getY(), 2);
        assertEquals(vampireCastleBuilding.checkPathCycle(world), true);
        assertEquals(vampireCastleBuilding.getPathCycle() ,0);
        //assertEquals(vampireCastleBuilding.getCharacterStepOn(), false);
        assertEquals(vampireCastleBuilding.getEnemies().size(), 0);
        vampireCastleBuilding.setPathCycle(3);
        assertEquals(vampireCastleBuilding.checkPathCycle(world), false);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        vampireCastleBuilding.addEnemy(slug);
        vampireCastleBuilding.addEnemy(vampire);
        assertEquals(vampireCastleBuilding.getEnemies().size(), 2);
        vampireCastleBuilding.removeEnemy(slug);
        assertEquals(vampireCastleBuilding.getEnemies().size(), 1);
        //vampireCastleBuilding.characterSteppingOn();
        // assertEquals(vampireCastleBuilding.getCharacterStepOn(), true);
        //vampireCastleBuilding.characterLeave();
        //assertEquals(vampireCastleBuilding.getCharacterStepOn(), false);
        assertNotEquals(vampireCastleBuilding.getNearestPath(world), null);
        vampireCastleBuilding.spawnVampire(world);
        assertEquals(world.getEnemy().get(0).getType(), "Vampire");
    }

    @Test
    public void testZombiePit() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        ZombiePit zombiePit = new ZombiePit(x, y);
        assertEquals(zombiePit.getX(), 1);
        assertEquals(zombiePit.getY(), 2);
        assertEquals(zombiePit.getPathCycle() ,0);
        //assertEquals(zombiePit.getCharacterStepOn(), false);
        assertEquals(zombiePit.getEnemies().size(), 0);
        zombiePit.setPathCycle(3);
        assertEquals(zombiePit.getPathCycle(), 3);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        zombiePit.addEnemy(slug);
        zombiePit.addEnemy(vampire);
        assertEquals(zombiePit.getEnemies().size(), 2);
        zombiePit.removeEnemy(slug);
        assertEquals(zombiePit.getEnemies().size(), 1);
        //zombiePit.characterSteppingOn();
        //assertEquals(zombiePit.getCharacterStepOn(), true);
        //zombiePit.characterLeave();
        //assertEquals(zombiePit.getCharacterStepOn(), false);
        assertNotEquals(zombiePit.getNearestPath(world), null);
        zombiePit.spawnZombie(world);
        assertEquals(world.getEnemy().get(0).getType(), "Zombie");
    }

    @Test
    public void testTower() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        // SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        // SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Tower tower = new Tower(position.getX(), position.getY());
        // assertEquals(tower.getX(), 1);
        // assertEquals(tower.getY(), 2);
        assertEquals(tower.getPathCycle() ,0);
        // assertEquals(tower.getCharacterStepOn(), false);
        assertEquals(tower.getEnemies().size(), 0);
        tower.setPathCycle(3);
        assertEquals(tower.getPathCycle(), 3);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        tower.addEnemy(slug);
        tower.addEnemy(vampire);
        assertEquals(tower.getEnemies().size(), 2);
        tower.removeEnemy(slug);
        assertEquals(tower.getEnemies().size(), 1);
        // tower.characterSteppingOn();
        // assertEquals(tower.getCharacterStepOn(), true);
        // tower.characterLeave();
        // assertEquals(tower.getCharacterStepOn(), false);
        assertNotEquals(tower.getNearestPath(world), null);
        assertEquals(tower.getShootRadius(), 5);
        assertEquals(tower.getDistance(3, 4), (Math.sqrt(Math.pow(2, 2) - Math.pow(2, 2))));
        tower.attack(world);
        vampire.setInBattle(true);
        world.getEnemy().add(vampire);
        assertEquals(vampire.getHP(), 800);//???

    }

    @Test
    public void testVillage() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Village village = new Village(x, y);
        assertEquals(village.getX(), 1);
        assertEquals(village.getY(), 2);
        assertEquals(village.getPathCycle() ,0);
        // assertEquals(village.getCharacterStepOn(), false);
        assertEquals(village.getEnemies().size(), 0);
        village.setPathCycle(3);
        assertEquals(village.getPathCycle(), 3);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        village.addEnemy(slug);
        village.addEnemy(vampire);
        assertEquals(village.getEnemies().size(), 2);
        village.removeEnemy(slug);
        assertEquals(village.getEnemies().size(), 1);
        // village.characterSteppingOn();
        // assertEquals(village.getCharacterStepOn(), true);
        // village.characterLeave();
        // assertEquals(village.getCharacterStepOn(), false);
        assertNotEquals(village.getNearestPath(world), null);
        assertEquals(village.getHpGain(), 100);
        Character character = new Character(position);
        // village.characterSteppingOn();
        character.setHp(1);
        village.increaseHp(character);
        assertEquals(character.getHp(), 101);
    }

    @Test
    public void testBarracks() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Barracks barracks = new Barracks(x, y);
        assertEquals(barracks.getX(), 1);
        assertEquals(barracks.getY(), 2);
        assertEquals(barracks.getPathCycle() ,0);
        // assertEquals(barracks.getCharacterStepOn(), false);
        assertEquals(barracks.getEnemies().size(), 0);
        barracks.setPathCycle(3);
        assertEquals(barracks.getPathCycle(), 3);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        barracks.addEnemy(slug);
        barracks.addEnemy(vampire);
        assertEquals(barracks.getEnemies().size(), 2);
        barracks.removeEnemy(slug);
        assertEquals(barracks.getEnemies().size(), 1);
        // barracks.characterSteppingOn();
        // assertEquals(barracks.getCharacterStepOn(), true);
        // barracks.characterLeave();
        // assertEquals(barracks.getCharacterStepOn(), false);
        assertNotEquals(barracks.getNearestPath(world), null);
        // barracks.characterSteppingOn();
        // assertEquals(barracks.getCharacterStepOn(), true);
        barracks.produceAlly(world);
        assertEquals(world.getAllies().size(), 1);
        // assertEquals(barracks.getCharacterStepOn(), true);
    }

    @Test
    public void testTrap() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Trap trap = new Trap(x, y);
        assertEquals(trap.getX(), 1);
        assertEquals(trap.getY(), 2);
        assertEquals(trap.getPathCycle() ,0);
        // assertEquals(trap.getCharacterStepOn(), false);
        assertEquals(trap.getEnemies().size(), 0);
        trap.setPathCycle(3);
        assertEquals(trap.getPathCycle(), 3);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        trap.addEnemy(slug);
        trap.addEnemy(vampire);
        assertEquals(trap.getEnemies().size(), 2);
        trap.removeEnemy(slug);
        assertEquals(trap.getEnemies().size(), 1);
        // trap.characterSteppingOn();
        // assertEquals(trap.getCharacterStepOn(), true);
        // trap.characterLeave();
        // assertEquals(trap.getCharacterStepOn(), false);
        assertNotEquals(trap.getNearestPath(world), null);
        assertEquals(trap.getDamage(), 4);
        // world.getBuildings().add(trap);
        // //Can't add buliding in world
        // assertEquals(world.getBuildings().size(), 1);
        trap.destroyTrap(world);
        assertEquals(world.getBuildings().size(), 0);
        //trap.exertDamage(world);
        //assertEquals(vampire.getHP(), 796);// Should kill the ememy
    }

    @Test
    public void testCampfire() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Campfire campfire = new Campfire(x, y);
        assertEquals(campfire.getX(), 1);
        assertEquals(campfire.getY(), 2);
        assertEquals(campfire.getPathCycle() ,0);
        // assertEquals(campfire.getCharacterStepOn(), false);
        assertEquals(campfire.getEnemies().size(), 0);
        campfire.setPathCycle(3);
        assertEquals(campfire.getPathCycle(), 3);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        campfire.addEnemy(slug);
        campfire.addEnemy(vampire);
        assertEquals(campfire.getEnemies().size(), 2);
        campfire.removeEnemy(slug);
        assertEquals(campfire.getEnemies().size(), 1);
        // campfire.characterSteppingOn();
        // assertEquals(campfire.getCharacterStepOn(), true);
        // campfire.characterLeave();
        // assertEquals(campfire.getCharacterStepOn(), false);
        assertNotEquals(campfire.getNearestPath(world), null);
        assertEquals(campfire.getcampRadius(), 3);
        Character character = new Character(position);
        campfire.doubleDamage(character);
        assertEquals(vampire.getHP(), 600);
    }

    @Test
public void testHeroCastle() {
    List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        HeroCastle heroCastle = new HeroCastle(x, y);
        assertEquals(heroCastle.getX(), 1);
        assertEquals(heroCastle.getY(), 2);
        assertEquals(heroCastle.getPathCycle() ,0);
        // assertEquals(heroCastle.getCharacterStepOn(), false);
        assertEquals(heroCastle.getEnemies().size(), 0);
        heroCastle.setPathCycle(3);
        assertEquals(heroCastle.getPathCycle(), 3);
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        heroCastle.addEnemy(slug);
        heroCastle.addEnemy(vampire);
        assertEquals(heroCastle.getEnemies().size(), 2);
        heroCastle.removeEnemy(slug);
        assertEquals(heroCastle.getEnemies().size(), 1);
        // heroCastle.characterSteppingOn();
        // assertEquals(heroCastle.getCharacterStepOn(), true);
        // heroCastle.characterLeave();
        // assertEquals(heroCastle.getCharacterStepOn(), false);
        assertNotEquals(heroCastle.getNearestPath(world), null);
        assertEquals(heroCastle.checkPathType(), true);
        heroCastle.setPathCycle(1);
        assertEquals(heroCastle.checkPathCycle(), false);
        assertEquals(heroCastle.getOfferWindow(), false);
        heroCastle.openOfferWindow();
        assertEquals(heroCastle.getOfferWindow(), true);
        heroCastle.closeOfferWindow();
        assertEquals(heroCastle.getOfferWindow(), false);
    }
}
