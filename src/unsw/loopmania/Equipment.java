package unsw.loopmania;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public class Equipment {
    private Item[] items;
    private List<Item> inv;
    public Equipment(List<Item> unequippedInventoryItems) {
        items = new BasicItem[4];
        this.inv = unequippedInventoryItems;
    }
    /**
     * add item to be equipped and remove it from the inventory 
     * @param item to be equipped
     * @return equipped item to be spawned by the GUI
     */
    public boolean equip(Item item) {
        int index = item.getSlot();
        // if item is not equippable, return false
        if (item.getType() == ItemType.OTHER) {
            return false;
        }
        // remove item from inventory to add to equipment slots
        inv.remove(item);
        if (items[index] != null) {
            // take current item in index and send to unequippedInventory
            unEquip(index);
        }
        items[index] = item;
        return true;
    }

    public boolean unEquip(int slot) {
        inv.add(items[slot]);
        items[slot] = null;
        return true;
    }

    /**
     * spawns the item inside the equippedItems slot
     * @param index of item inside equippedItems
     * @param type of item to be spawned
     * @return equipped item to be spawned in GUI
     */
    public Item spawnEquippedItem(int index, ItemType type) {
        Item item;
        SimpleIntegerProperty equipX = new SimpleIntegerProperty(index);
        SimpleIntegerProperty equipY = new SimpleIntegerProperty(0);
        switch (type) {
            case SWORD:
                item = new Sword(equipX, equipY);
                break;
            case STAKE:
                item = new Stake(equipX, equipY);
                break;
            case STAFF:
                item = new Staff(equipX, equipY);
                break;
            case HELMET:
                item = new Helmet(equipX, equipY);
                break;
            case ARMOUR:
                item = new Armour(equipX, equipY);
                break;
            case SHIELD:
                item = new Shield(equipX, equipY);
                break;
            default:
                item = null;
        }
        return item;
    }


}
