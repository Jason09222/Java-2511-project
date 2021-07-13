package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Gold;
import unsw.loopmania.HealthPotion;
import unsw.loopmania.Helmet;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.RareItem;
import unsw.loopmania.Shield;
import unsw.loopmania.Slug;
import unsw.loopmania.Staff;
import unsw.loopmania.Stake;
import unsw.loopmania.Sword;
import unsw.loopmania.TheOneRing;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;
import unsw.loopmania.Armour;
import unsw.loopmania.Character;

public class BasicItemTest {
    @Test
    public void ItemSword() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Sword sword = new Sword(x, y);
        assertEquals(sword.getDamage(), 200);
        assertEquals(sword.getPrice(), 1000);
        assertEquals(sword.getType(), "Sword");
        assertEquals(sword.getX(), 1);
        assertEquals(sword.getY(), 2);
    }

    @Test
    public void ItemGold() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Gold gold = new Gold(x, y);
        assertEquals(gold.getValue(), 200);
        assertEquals(gold.getType(), "Gold");
        assertEquals(gold.getX(), 1);
        assertEquals(gold.getY(), 2);
    }

    @Test
    public void ItemTheOneRing() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        TheOneRing theonering = new TheOneRing(x, y);
        Character character = new Character(null);
        character.setHp(0);
        character.useTheOneRing(theonering);
        assertEquals(character.getHp(),500);
        assertEquals(theonering.getType(), "TheOneRing");
        assertEquals(theonering.getX(), 1);
        assertEquals(theonering.getY(), 2);
    }

    @Test
    public void ItemStake() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Stake stake = new Stake(x, y);
        Character character = new Character(null);
        Vampire vampire = new Vampire(null);
        Slug slug = new Slug(null);
        character.useStake(stake, vampire);
        character.useStake(stake, slug);
        assertEquals(vampire.getHP(), -300);
        assertEquals(slug.getHP(), -240);
        assertEquals(stake.getDamage(vampire), 300);
        assertEquals(stake.getDamage(slug), 150);
        assertEquals(stake.getPrice(), 1500);
        assertEquals(stake.getType(), "Stake");
        assertEquals(stake.getX(), 1);
        assertEquals(stake.getY(), 2);
    }

    @Test
    public void ItemStaff() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Staff staff = new Staff(x, y);
        LoopManiaWorld loopManiaWorld = new LoopManiaWorld(1, 2, new ArrayList<>());
        Vampire vampire = new Vampire(null);
        Slug slug = new Slug(null);
        Character character = new Character(null);
        // character.useStaff(staff, vampire, loopManiaWorld);
        // character.useStaff(staff, slug, loopManiaWorld);
        // assertEquals(vampire.getHP(), -100);
        // assertEquals(slug.getHP(), -190);
        assertEquals(staff.getDamage(), 100);
        assertEquals(staff.getPrice(), 2000);
        assertEquals(staff.getType(), "Staff");
        assertEquals(staff.getX(), 1);
        assertEquals(staff.getY(), 2);
        // assertEquals(staff.trance(vampire, loopManiaWorld), void);
    }

    @Test
    public void ItemShield() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Shield shield = new Shield(x, y);
        assertEquals(shield.getDefense(), 200);
        assertEquals(shield.getPrice(), 2000);
        assertEquals(shield.getType(), "Shield");
        assertEquals(shield.getX(), 1);
        assertEquals(shield.getY(), 2);
    }

    @Test
    public void ItemRareItem() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        RareItem rareItem = new RareItem(x, y, "TheOneRing");
        assertEquals(rareItem.getType(), "TheOneRing");
        assertEquals(rareItem.getX(), 1);
        assertEquals(rareItem.getY(), 2);
    }

    @Test
    public void ItemHelmet() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Helmet helmet = new Helmet(x, y);
        Vampire vampire = new Vampire(null);
        Slug slug = new Slug(null);
        Character character = new Character(null);
        assertEquals(character.getHp()-slug.getDamage()+helmet.getDefense(), -348);
        assertEquals(helmet.getDefense(), 150);
        assertEquals(helmet.getPrice(), 1500);
        assertEquals(helmet.getType(), "Helmet");
        assertEquals(helmet.getX(), 1);
        assertEquals(helmet.getY(), 2);
    }

    @Test
    public void HealthPotion() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        HealthPotion healthPotion = new HealthPotion(x, y);
        Character character = new Character(null);
        character.setHp(200);
        character.useHealthPotion(healthPotion);
        assertEquals(character.getHp(), 400);
        assertEquals(healthPotion.getHealth(), 200);
        assertEquals(healthPotion.getPrice(), 5000);
        assertEquals(healthPotion.getType(), "HealthPotion");
        assertEquals(healthPotion.getX(), 1);
        assertEquals(healthPotion.getY(), 2);
    }

    @Test
    public void ItemArmour() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        Armour armour = new Armour(x, y);
        Slug slug = new Slug(null);
        assertEquals(armour.getDefense(slug), 499);
        assertEquals(armour.getPrice(), 1000);
        assertEquals(armour.getType(), "Armour");
        assertEquals(armour.getX(), 1);
        assertEquals(armour.getY(), 2);
    }
}

