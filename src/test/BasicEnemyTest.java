package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.Ally;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;

public class BasicEnemyTest {
    @Test
    public void testSlug() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        position.moveUpPath();
        position.moveDownPath();
        Slug slug = new Slug(position);
        assertEquals("Slug", slug.getType());
        assertEquals(300, slug.getHP());
        assertEquals(100, slug.getExp());
        assertEquals(20, slug.getDamage());
        assertEquals(1, slug.getFightRadius());
        assertEquals(1, slug.getSupportRadius());
        assertFalse(slug.getIsWeak());
        assertEquals(100, slug.getGold());
        assertEquals(2, slug.getSpeed());
        assertFalse(slug.getInBattle());
        assertEquals("Up", slug.getLastDirec());
        slug.setInBattle(true);
        assertTrue(slug.getInBattle());
        slug.setLastDirec("Down");
        assertEquals("Down", slug.getLastDirec());
        Ally ally = new Ally(position);
        slug.attack_ally(ally);
        assertEquals(80, ally.getHp());
        assertEquals(2, slug.getDistance(3, 4));
        Character character = new Character(position);
        slug.attack_character(character);

    }

    @Test
    public void testVampire() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Vampire vampire = new Vampire(position);
        Ally ally = new Ally(position);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        vampire.attack_ally(ally);
        assertNotEquals(300, ally.getHp());
        Character character = new Character(position);
        vampire.attack_character(character);
        vampire.attack_character(character);
        vampire.attack_character(character);
        vampire.attack_character(character);
        vampire.attack_character(character);
        vampire.attack_character(character);
        vampire.attack_character(character);
        vampire.attack_character(character);
        vampire.attack_character(character);
        assertNotEquals(500, character.getHp());
        vampire.setCriticalPoss(100);
        assertEquals(100, vampire.getCriticalPoss());
        vampire.setCriticalBack();
        assertEquals(10, vampire.getCriticalPoss());
        vampire.move();
    }

    @Test
    public void testZombie() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        int index = orderedPath.indexOf(pair1);
        PathPosition position = new PathPosition(index, orderedPath);
        Zombie zombie = new Zombie(position);
        Ally ally = new Ally(position);
        zombie.attack_ally(ally);
    }
}
