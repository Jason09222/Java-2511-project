package unsw.loopmania;
import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private BasicItem[] items;
    private List<Item> inv;
    public Equipment(List<Item> unequippedInventoryItems) {
        items = new BasicItem[4];
        this.inv = unequippedInventoryItems;
    }

    public boolean equip(BasicItem item) {
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


}
