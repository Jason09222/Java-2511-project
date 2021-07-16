package test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import unsw.loopmania.Character;
import unsw.loopmania.HealthPotion;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Sword;
import unsw.loopmania.BasicItem;
import unsw.loopmania.ItemType;

public class EquipmentTest {
    @Test
    public void testEquipment() {
        List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Integer, Integer> pair2 = new Pair<>(2, 2);
        list.add(pair1);
        list.add(pair2);
        LoopManiaWorld world = new LoopManiaWorld(5, 5, list);
        BasicItem sword = world.addUnequippedItem(ItemType.SWORD);
    //    assertEquals(sword.getType(), ItemType.SWORD);
        BasicItem helmet = world.addUnequippedItem(ItemType.HELMET);
    //    assertEquals(helmet.getType(), ItemType.HELMET);
        assertTrue(world.equipItem(sword));
        assertTrue(world.equipItem(helmet));
    }


}
