package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import unsw.loopmania.Character;
import unsw.loopmania.HealthPotion;
//import unsw.loopmania.HealthPotion;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.TheOneRing;

public class CharacterTest {
    @Test
    public void testCharacter() {
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
        character.setHp(-1);
        character.setHp(10000);
        character.setHp(500);
        character.setHp(300);
        assertEquals(300, character.getHp().get());
        assertEquals(100, character.getDamage());
        character.setInBattle(true);
        assertEquals(true, character.getInBattle());
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        HealthPotion healthPotion = new HealthPotion(x, y);
        // character.useHealthPotion(healthPotion);
        // assertEquals(500, character.getHp());
        character.setHp(100);
        TheOneRing theOneRing = new TheOneRing(x, y);
        character.useTheOneRing(theOneRing);
        assertEquals(500, character.getHp().get());
        character.getStuntimes();
        character.setStunTimes(2);
        character.getHpProgress();
        character.setDamage(2);
        character.getInBattle();
        Slug e = new Slug(position);
        character.attack(e, world.getEquipItems());
        character.useHealthPotion(healthPotion);
        character.setDamageBack();

        // TODO attack
    }
}
