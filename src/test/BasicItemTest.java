package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Gold;
import unsw.loopmania.HealthPotion;
import unsw.loopmania.Helmet;
import unsw.loopmania.ItemType;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.RareItem;
import unsw.loopmania.Shield;
import unsw.loopmania.Slug;
import unsw.loopmania.Staff;
import unsw.loopmania.Stake;
import unsw.loopmania.Sword;
import unsw.loopmania.TheOneRing;
import unsw.loopmania.TreeStump;
import unsw.loopmania.Vampire;
import unsw.loopmania.Anduril;
// import unsw.loopmania.Zombie;
import unsw.loopmania.Armour;
import unsw.loopmania.Character;
import unsw.loopmania.Doggie;
import unsw.loopmania.DoggieCoin;

import org.javatuples.Pair;

public class BasicItemTest {
    @Test
    public void ItemSword() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Sword armour = new Sword(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.SWORD);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertTrue(armour.canBePurchased());
        assertEquals(armour.getSlot(), 0);
        world.setCharacter(character);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void ItemGold() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Gold armour = new Gold(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.OTHER);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertFalse(armour.canBePurchased());
        assertEquals(armour.getSlot(), 4);
        world.setCharacter(character);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }


    @Test
    public void ItemDoggieCoin() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        DoggieCoin armour = new DoggieCoin(x, y, ItemType.OTHER);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.OTHER);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertTrue(armour.canBePurchased());
        assertEquals(armour.getSlot(), 4);
        world.setCharacter(character);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void ItemTheOneRing() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        TheOneRing armour = new TheOneRing(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.OTHER);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertFalse(armour.canBePurchased());
        assertEquals(armour.getSlot(), 4);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }


    @Test
    public void ItemAnduril() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Anduril armour = new Anduril(x, y, ItemType.OTHER);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.OTHER);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertFalse(armour.canBePurchased());
        assertEquals(armour.getSlot(), 4);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }





    @Test
    public void ItemStake() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Stake armour = new Stake(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.STAKE);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertTrue(armour.canBePurchased());
        assertEquals(armour.getSlot(), 0);
        world.setCharacter(character);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void ItemStaff() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Staff armour = new Staff(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.STAFF);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertTrue(armour.canBePurchased());
        assertEquals(armour.getSlot(), 0);
        world.setCharacter(character);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void ItemShield() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Shield armour = new Shield(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.SHIELD);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertTrue(armour.canBePurchased());
        assertEquals(armour.getSlot(), 2);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }



    @Test
    public void ItemHelmet() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Helmet armour = new Helmet(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.HELMET);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertTrue(armour.canBePurchased());
        assertEquals(armour.getSlot(), 1);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void ItemTreeStump() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        TreeStump armour = new TreeStump(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.SHIELD);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertFalse(armour.canBePurchased());
        assertEquals(armour.getSlot(), 2);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }


    @Test
    public void HealthPotion() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        HealthPotion armour = new HealthPotion(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getType(), ItemType.HEALTHPOTION);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertFalse(armour.canBePurchased());
        assertEquals(armour.getSlot(), 4);
        world.setCharacter(character);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void ItemArmour() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Armour armour = new Armour(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getDefense(slug), 10);
        assertEquals(armour.getType(), ItemType.ARMOUR);
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
        Character character = new Character(position);
        armour.useDuringBattle(slug, character);
        assertTrue(armour.canBePurchased());
        assertEquals(armour.getSlot(), 3);
        armour.characterStepOn(world, new ArrayList<>(), new ArrayList<>());
    }
}

