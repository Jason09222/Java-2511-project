package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

import java.lang.Math;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.effect.BlurType;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one entity
 * can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    public static final int equippedInventoryWidth = 4;
    public static final int equippedInventoryHeight = 1;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    private int pathCycle = 0;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    // TODO = add more lists for other entities, for equipped inventory items,
    // etc...

    // TODO = expand the range of enemies
    private List<BasicEnemy> enemies;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    // private Inventory unequippedInventoryItems;
    private List<Item> unequippedInventoryItems;
    private List<BasicItem> unPickedItem;
    private Equipment equippedItems;

    // TODO = expand the range of buildings
    private List<VampireCastleBuilding> buildingEntities;
    private List<Building> campfires;
    private List<Building> buildings;

    private List<Ally> allies;

    private int goldOwned;
    private int potionsOwned;
    private int experience;
    private int ringOwned;
    
    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse
     * them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     *
     * @param width       width of world in number of cells
     * @param height      height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing
     *                    position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        equippedItems = new Equipment(unequippedInventoryItems);
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        goldOwned = 0;
        potionsOwned = 0;
        experience = 0;
        ringOwned = 0;
        buildings = new ArrayList<>();
        allies = new ArrayList<>();
        campfires = new ArrayList<>();
        unPickedItem = new ArrayList<>();
    }

    public List<Ally> getAllies() {
        return this.allies;
    }

    public List<BasicEnemy> getEnemy() {
        return this.enemies;
    }

    public List<Building> getCampfire() {
        return this.campfires;
    }

    public List<Pair<Integer, Integer>> getOrderedPath() {
        return this.orderedPath;
    }

    public List<BasicItem> getUnpickedItems() {
        return this.unPickedItem;
    }

    public void addAlly(Ally ally) {
        allies.add(ally);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity
     * out of the file
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the
     * world)
     *
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods
        // like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     *
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies() {
        // TODO = expand this very basic version
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
        if (pos != null) {
            int indexInPath = orderedPath.indexOf(pos);
            // BasicEnemy enemy = new BasicEnemy(new PathPosition(indexInPath,
            // orderedPath));
            BasicEnemy enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }


        // Spawn vampires from vampire castle
        for (Building b : this.buildings) {
            if (b instanceof VampireCastleBuilding) {
                VampireCastleBuilding v = (VampireCastleBuilding)b;
                if (v.checkPathCycle(this)) {
                    Vampire newVam = v.spawnVampire(this);
                    spawningEnemies.add(newVam);
                }
            }

            if (b instanceof ZombiePit) {
                ZombiePit z = (ZombiePit) b;
                if (z.checkPathCycle(this)) {
                    Zombie newZom = z.spawnZombie(this);
                    spawningEnemies.add(newZom);
                }
            }
        }   
        return spawningEnemies;
    }


    /**
     * spawns items if the conditions warrant it, adds to world
     *
     * @return list of the gold to be displayed on screen
     */
    public List<BasicItem> possiblySpawnItems() {
        // Pair<Integer, Integer> pos1 = possiblyGetBasicItemSpawnPosition();
        // Pair<Integer, Integer> pos2 = possiblyGetBasicItemSpawnPosition();
        List<BasicItem> spawningItems = new ArrayList<>();
        // if (pos1 != null && pos2 != null) {
        //     int indexInPath1 = orderedPath.indexOf(pos1);
        //     int indexInPath2 = orderedPath.indexOf(pos2);
        //     PathPosition newPathPosition1 = new PathPosition(indexInPath1, orderedPath);
        //     PathPosition newPathPosition2 = new PathPosition(indexInPath2, orderedPath);
        //     BasicItem gold = new Gold(newPathPosition1.getX(), newPathPosition1.getY());
        //     BasicItem healthPotion = new HealthPotion(newPathPosition2.getX(), newPathPosition2.getY());
        //     unPickedItem.add(gold);
        //     spawningItems.add(gold);
        //     unPickedItem.add(healthPotion);
        //     spawningItems.add(healthPotion);
        // }
        if (unPickedItem.size() < 2) {
            boolean goldExist = false;
            boolean healthPotionExist = false;
            for (BasicItem item : unPickedItem) {
                if (item.getType() == ItemType.OTHER) {
                    goldExist = true;
                }
                if (item.getType() == ItemType.HEALTHPOTION) {
                    healthPotionExist = true;
                }
            }
            Random rand = new Random();
            if (goldExist == false && this.pathCycle % (3 * orderedPath.size()) == 0) {
                int indexInOrderedPath = rand.nextInt(orderedPath.size() - 1);
                Pair<Integer, Integer> newPos = orderedPath.get(indexInOrderedPath);

                BasicItem gold = new Gold(new SimpleIntegerProperty(newPos.getValue0()), new SimpleIntegerProperty(newPos.getValue1()));
                unPickedItem.add(gold);
                spawningItems.add(gold);
            }

            if (healthPotionExist == false && this.pathCycle % (4 * orderedPath.size()) == 0) {
                int indexInOrderedPath = rand.nextInt(orderedPath.size() - 1);
                Pair<Integer, Integer> newPos = orderedPath.get(indexInOrderedPath);
                BasicItem healthPotion = new HealthPotion(new SimpleIntegerProperty(newPos.getValue0()), new SimpleIntegerProperty(newPos.getValue1()));
                unPickedItem.add(healthPotion);
                spawningItems.add(healthPotion);
            }
        }
        return spawningItems;
    }

    //  /**
    //  * get a randomly generated position which could be used to spawn an item
    //  *
    //  * @return null if random choice is that wont be spawning an enemy or it isn't
    //  *         possible, or random coordinate pair if should go ahead
    //  */
    // private Pair<Integer, Integer> possiblyGetBasicItemSpawnPosition() {

    //     // has a chance spawning a basic item on a tile the character isn't on or
    //     // immediately before or after (currently space required = 2)...
    //     Random rand = new Random();
    //     int choice = rand.nextInt(2);
    //     if ((choice == 0) && (enemies.size() < 2)) {
    //         List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
    //         int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
    //         // inclusive start and exclusive end of range of positions not allowed
    //         int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
    //         int endNotAllowed = (indexPosition + 3) % orderedPath.size();
    //         // note terminating condition has to be != rather than < since wrap around...
    //         for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
    //             orderedPathSpawnCandidates.add(orderedPath.get(i));
    //         }

    //         // choose random choice
    //         Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates
    //                 .get(rand.nextInt(orderedPathSpawnCandidates.size()));

    //         return spawnPosition;
    //     }
    //     return null;
    // }

    /**
     * kill an enemy
     *
     * @param enemy enemy to be killed
     */
    public void killEnemy(BasicEnemy enemy) {
        enemy.destroy();
        enemies.remove(enemy);
    }

    public void killAlly(Ally ally) {
        ally.destroy();
        allies.remove(ally);
    }

    /**
     * run the expected battles in the world, based on current world state
     *
     * @return list of enemies which have been killed
     */
    public List<BasicEnemy> runBattles() {
        // TODO = modify this - currently the character automatically wins all battles
        // without any damage!
        List<BasicEnemy> defeatedEnemies = new ArrayList<BasicEnemy>();
        List<Ally> defeatedAllies = new ArrayList<Ally>();
        List<BasicEnemy> transferZombies = new ArrayList<BasicEnemy>();
        boolean inBattle = false;
        for (BasicEnemy e : enemies) {
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            // TODO = you should implement different RHS on this inequality, based on
            // influence radii and battle radii
            boolean hasAttacked = false;
            for (Ally ally : allies) {
                if (ally.getHp() <= 0) {
                    continue;
                }
                if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) <= Math
                        .pow(e.getFightRadius(), 2)) {
                    character.setInBattle(true);
                    inBattle = true;
                    e.attack_ally(ally);
                    hasAttacked = true;
                    if (ally.getHp() <= 0) {
                        if (e.getType().equals("Zombie")) {
                            Random rand = new Random();
                            int int_random = rand.nextInt(5);
                            if (int_random == 0) {
                                BasicEnemy newZombie = new Zombie(ally.getPathPosition());
                                transferZombies.add(newZombie);
                            }
                        }
                        defeatedAllies.add(ally);
                    }
                    break;
                }
            }
            if (!hasAttacked) {
                if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) <= Math
                        .pow(e.getFightRadius(), 2)) {

                    character.setInBattle(true);
                    inBattle = true;
                    // if (shield && e.getType().equals)
                    e.attack_character(character);
                }
            }

            for (BasicEnemy enemy : transferZombies) {
                enemies.add(enemy);
            }
        }

        for (Ally ally : allies) {
            for (BasicEnemy e : enemies) {
                if (e.getHP() <= 0) {
                    continue;
                }
                if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) <= 4) {
                    inBattle = true;
                    ally.attack(e);
                    if (e.getHP() <= 0) {
                        defeatedEnemies.add(e);
                    }
                    break;
                }
            }
        }

        for (BasicEnemy e : enemies) {
            if (e.getHP() <= 0) {
                continue;
            }
            // add character attacked
            if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) <= 4) {
                inBattle = true;
                character.attack(e);
                if (e.getHP() <= 0) {
                    defeatedEnemies.add(e);
                }
                break;
            }
        }
        for (BasicEnemy e : defeatedEnemies) {
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from
            // the enemies list
            // if we killEnemy in prior loop, we get
            // java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }

        for (Ally ally : defeatedAllies) {
            killAlly(ally);
        }
        if (character.getHp() <= 0) {
            // TODO
            // Lose Game;
        }
        if (!inBattle) {

            character.setInBattle(false);
        }

        return defeatedEnemies;
    }



    public ItemType generateItem() {
        int totalRewards = 8;
        Random rand = new Random();
        int result = rand.nextInt(1000) % totalRewards;
        switch (result) {
            case 1:
                return ItemType.ARMOUR;
            case 2:
                return ItemType.HEALTHPOTION;
    
            case 3:
                return ItemType.HELMET;
            case 4:
                return ItemType.SHIELD;
            case 5:
                return ItemType.STAFF;
            case 6:
                return ItemType.STAKE;
            case 7:
                return ItemType.SWORD;
            default:
                return null;
        }
    }


    /**
     * spawn a sword in the world and return the sword entity
     *
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Sword addUnequippedSword() {
        // TODO = expand this - we would like to be able to add multiple types of items,
        // apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(sword);
        return sword;
    }

    

    
    /**
     * spawn an item in the world and return the item entity
     *
     * @param type of item to be added
     * @return a item to be spawned in the controller as a JavaFX node
     */

    public BasicItem addUnequippedItem(ItemType type) {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
            // gives random amount of cash/experience reward for discarding oldest item
            Random rand = new Random();
            int result = rand.nextInt(10) % 2;
            switch (result) {
                case 0:
                    addExperience(rand.nextInt(10));
                    break;
                case 1:
                    addGold(rand.nextInt(10));
                    break;
                default:
                    break;
            }
        }
        SimpleIntegerProperty x = new SimpleIntegerProperty(firstAvailableSlot.getValue0());
        SimpleIntegerProperty y = new SimpleIntegerProperty(firstAvailableSlot.getValue1());
        // insert new item as it is now we know we have a slot available
        BasicItem item;
        switch (type) {
            case SWORD:
                item = new Sword(x, y);
                break;
            case STAKE:
                item = new Stake(x, y);
                break;
            case STAFF:
                item = new Staff(x, y);
                break;
            case HELMET:
                item = new Helmet(x, y);
                break;
            case ARMOUR:
                item = new Armour(x, y);
                break;
            case SHIELD:
                item = new Shield(x, y);
                break;
            case HEALTHPOTION:
                potionsOwned += 1;
                item = null;
                break;
            default:
                item = null;
        }
        unequippedInventoryItems.add(item);
        return item;
    }


    /**
     * removes the item from equippedItems list and adds it back to
     * unequippedInventory
     * @param slot of item to be removed
     * @return
     */
    public boolean unEquipItem(int slot) {
        // TODO = spawn the item back into the inventory
        equippedItems.unEquip(slot);
        return equippedItems.unEquip(slot);
    }
    /**
     * moves an item from unequippedInventory to equippedItems.
     * Deletes the item and creates a new copy of it inside equippedItems
     * @param nodeX of the item in unequippedInventory
     * @param nodeY of the item in unequippedInventory
     * @return item to be spawned inside the equippedItems gridpane
     */
    public Item equipItemByCoordinates(int nodeX, int nodeY) {
        Item item = getUnequippedInventoryItemEntityByCoordinates(nodeX, nodeY);
        equippedItems.equip(item);
        Item equippedItem = equippedItems.spawnEquippedItem(item.getType().getIndex(), item.getType());
        item.destroy();
        return equippedItem;
    }





    /**
     * remove an item by x,y coordinates
     *
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
        Item item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything
     * immediately
     */
    public void runTickMoves() {

        if (!character.getInBattle()) {
            character.moveDownPath();
            updatePathCycle();
        }
        moveBasicEnemies();
        charactersStepOnBuilding();
        enemyStepOnBuilding();
        // turn ally back to enemy
        boolean battleEnd = true;
        for (BasicEnemy enemy : enemies) {
            if (enemy.getInBattle()) {
                battleEnd = false;
            }
        }
        if (battleEnd) {
            // kill all tranced allies
            for (Ally ally : allies) {
                if (!ally.getOriginalType().equals(null)) {
                    killAlly(ally);
                }
            }
        } else {
            // Tranced ally turns back to enemy
            for (Ally ally : allies) {
                PathPosition position = ally.getPathPosition();
                if (!ally.getOriginalType().equals(null)) {
                    if (ally.getRound() - 1 == 0) {
                        String type = ally.getOriginalType();
                        BasicEnemy enemy;
                        switch (type) {
                            case "Vampire":
                                enemy = new Vampire(position);
                                break;
                            case "Slug":
                                enemy = new Slug(position);
                                break;
                            default:
                                enemy = new Zombie(position);
                                break;
                        }
                        enemies.add(enemy);
                        
                        killAlly(ally);
                    } else {
                        ally.setRound(ally.getRound() - 1);
                    }
                }
            }
        }


        // Pick up gold or health potion
        List<BasicItem> toRemove = new ArrayList<>();
        for (BasicItem item: unPickedItem) {
            if (item instanceof Gold && character.getX() == item.getX() && character.getY() == item.getY()) {
                toRemove.add(item);
            }
        }

        for (BasicItem item: toRemove) {
            unPickedItem.remove(item);
            item.destroy();
            goldOwned += 200;
        }


        toRemove.clear();
        for (BasicItem item: unPickedItem) {
            if (item instanceof HealthPotion && character.getX() == item.getX() && character.getY() == item.getY()) {
                toRemove.add(item);
            }
        }

        for (BasicItem item: toRemove) {
            unPickedItem.remove(item);
            item.destroy();
            addPotion(1);
        }
        /*//pick up gold or health potion
        double goldDistance = Math.sqrt(Math.pow(character.getX(), 2) + Math.pow(character.getY(), 2));
        double healthPotionDistance = Math.sqrt(Math.pow(character.getX() - 3, 2) + Math.pow(character.getY() - 3, 2));
        if (goldDistance < 5) {
            for (BasicItem item : unPickedItem) {
                if (item.getType() == ItemType.OTHER) {
                    item.destroy();
                    unPickedItem.remove(item);
                    goldOwned += 200;
                    break;
                }

            }
        }
        if (healthPotionDistance < 5) {
            for (BasicItem item : unPickedItem) {
                if (item.getType() == ItemType.HEALTHPOTION) {
                    item.destroy();
                    unPickedItem.remove(item);
                    addPotion(1);
                    break;
                }

            }
        }*/

    }

    

    public void updatePathCycle() {
        this.pathCycle += 1;
        for (Building b : this.buildings) {
            b.setPathCycle(b.getPathCycle() + 1);
        }
    }

    /**
     * remove an item from the unequipped inventory
     *
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item) {
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates assumes that no 2
     * unequipped inventory items share x and y coordinates
     *
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Item getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Item e : unequippedInventoryItems) {
            if ((e.getX() == x) && (e.getY() == y)) {
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list
     * (this is ordered based on age in the starter code)
     *
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index) {
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the
     * unequipped inventory
     *
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem() {
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available
        // slot defined by looking row by row
        for (int y = 0; y < unequippedInventoryHeight; y++) {
            for (int x = 0; x < unequippedInventoryWidth; x++) {
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null) {
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     *
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x) {
        for (Card c : cardEntities) {
            if (c.getX() >= x) {
                c.x().set(c.getX() - 1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        // TODO = expand to more types of enemy

        for (BasicEnemy e : enemies) {
            for (int i = 0; i < e.getSpeed(); i++) {
                Building nearestCamp = this.getShortestCampfire(e);
                if (e.getType().equals("Vampire") && nearestCamp != null) {
                    getAwayFromCampfire(e);
                }

                if (character.getInBattle()
                        && e.getDistance(character.getX(), character.getY()) <= e.getSupportRadius()) {
                    supportMove(e);
                } else {
                    e.move();
                }
                // supportMove(e);
            }
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     *
     * @return null if random choice is that wont be spawning an enemy or it isn't
     *         possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition() {
        // TODO = modify this

        // has a chance spawning a basic enemy on a tile the character isn't on or
        // immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        int slugNum = 0;
        for (BasicEnemy enemy : enemies) {
            if (enemy instanceof Slug) {
                slugNum++;
            }
        }
        if ((choice == 0) && (slugNum < 2)){
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
            int endNotAllowed = (indexPosition + 3) % orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates
                    .get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    /*
    private Pair<Integer, Integer> possiblyGetVampireSpawnPosition(Building building) {
        Random rand = new Random();
        //int choice = rand.nextInt(2);
        if (building instanceof VampireCastleBuilding && building.getPathCycle() == 4) {
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
            int endNotAllowed = (indexPosition + 3)%orderedPath.size();
        // note terminating condition has to be != rather than < since wrap around...
            for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));
            building.setPathCycle(0);
            return spawnPosition;

        }
        return null;
    }
    */



    public int getPotions() {
        return this.potionsOwned;
    }

    public void addPotion(int numGained) {
        this.potionsOwned += numGained;
    }

    public void spendPotions() {
        int tempHP = character.getHp();
        if (this.getPotions() != 0) {
            if ((tempHP + 200) >= 500) {
                character.setHp(500);
            }
            else {
                character.setHp(tempHP + 200);
            }
            this.potionsOwned--;
        }
    }

    public int getGold() {
        return this.goldOwned;
    }

    public void addGold(int numGained) {
        this.goldOwned += numGained;
    }

    public void spendGold(int numLost) {
        this.goldOwned -= numLost;
    }

    public int getExperience() {
        return this.experience;
    }

    public void addExperience(int numGained) {
        this.experience += numGained;
    }

    public Building getShortestCampfire(BasicEnemy e) {
        if (this.getCampfire().isEmpty())
            return null;
        int shortest = 1000;
        Building tmp = new Campfire(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        for (Building b : this.getCampfire()) {
            int currDist = e.getDistance(b.getX(), b.getY());
            if (currDist < shortest) {
                tmp = b;
                shortest = currDist;
            }
        }
        return tmp;
    }

    public void getAwayFromCampfire(BasicEnemy e) {
        int shortest = 1000;
        int enemyPos = 0;
        int placeToGo = 0;
        int i = 0;
        boolean flag = false;
        for (Pair<Integer,Integer> pos : orderedPath) {
            boolean isAvailable = true;
            for (Building building : campfires) {
                Campfire campfire = (Campfire) building;
                if (campfire.getDistance(pos.getValue0(), pos.getValue1()) <= campfire.getcampRadius()) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                if (e.getDistance(pos.getValue0(), pos.getValue1()) < shortest) {
                    placeToGo = i;
                    shortest = e.getDistance(pos.getValue0(), pos.getValue1());
                }
            }
            i++;
            if (pos.getValue0() == e.getX() && pos.getValue1() == e.getY()) {
                flag = true;
                enemyPos++;
            }
            if (!flag) {
                enemyPos++;
            }
        }
        if (shortest == 1000) {
            e.move();
            return;
        } else {
            int len = orderedPath.size() / 2;
            if (enemyPos - placeToGo < len && enemyPos - placeToGo > 0) {
                e.moveUpPath();
            } else {
                e.moveDownPath();
            }
        }
    }

    public void addUnequippedInventory(BasicItem item) {
        
        this.unequippedInventoryItems.add(item);
    }

    public void generateTrophy(BasicEnemy e) {

        Random rand = new Random();
        int int_random = rand.nextInt(3);
        SimpleIntegerProperty x = e.x();
        SimpleIntegerProperty y = e.y();

       
        
        switch (int_random) {
            case 0:
                this.goldOwned += e.getGold();
                break;
            case 1:
                addUnequippedItem(generateItem());
                break;
            case 2:
                BasicItem healthP = new HealthPotion(x, y);
                addUnequippedInventory(healthP);
                break;
            case 3:
                generateCard();
                break;
            default:
                return;
            }
        }

        public void supportMove(BasicEnemy e) {

            int enemyX = e.getX();
            int enemyY = e.getY();
            int characterX = character.getX();
            int characterY = character.getY();

            int len = orderedPath.size() / 2;
            int start = 0;
            boolean isStart = false;
            boolean isEnd = false;
            int end = 0;
            for (Pair<Integer, Integer> pair : orderedPath) {
                int tmpX = pair.getValue0();
                int tmpY = pair.getValue1();
                if (!isStart) {
                    start += 1;
                }
                if (!isEnd) {
                    end += 1;
                }
                if (tmpX == enemyX && tmpY == enemyY) {
                    start += 1;
                    isStart = true;
                }
                if (tmpX == characterX && tmpY == characterY) {
                    end += 1;
                    isEnd = true;
                }
                if (isStart && isEnd) {
                    break;
                }
            }

            if (start - end < len && start - end > 0) {
                e.moveUpPath();
            } else {
                e.moveDownPath();
            }
        }


    public List<Building> getBuildings() {
        return this.buildings;
    }

    public Building createbuilding(String type, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Building newBuilding = null;
        if (!checkBuildingAlrdyExisted(x, y)) {
            switch(type) {
                case "Village":
                    if (checkPathTile(x, y)) newBuilding = new Village(x, y);
                    break;
                case "Barracks":
                    if (checkPathTile(x, y)) newBuilding = new Barracks(x, y);
                    break;
                case "Tower":
                    if (!checkPathTile(x, y) && checkAdjacentToPathTile(x, y)) newBuilding = new Tower(x, y);
                    break;
                case "Trap":
                    if (checkPathTile(x, y)) newBuilding = new Trap(x, y);
                    break;
                case "VampireCastleBuilding":
                    if (!checkPathTile(x, y) && checkAdjacentToPathTile(x, y)) newBuilding = new VampireCastleBuilding(x, y);
                    break;
                case "ZombiePit":
                    if (!checkPathTile(x, y) && checkAdjacentToPathTile(x, y)) newBuilding = new ZombiePit(x, y);
                    break;
                case "Campfire":
                    if (!checkPathTile(x, y)) newBuilding = new Campfire(x, y);
                    break;
            }
        }

        if (newBuilding != null) this.buildings.add(newBuilding);
        return newBuilding;
    }

    public boolean checkBuildingAlrdyExisted(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        for (Building b : this.buildings) {
            if (x.get() == b.getX() && y.get() == b.getY()) {
                return true;
            }
        }
        return false;
    }

    public void charactersStepOnBuilding() {
        for (Building b : this.buildings) {
            int destX = b.getX();
            int destY = b.getY();
            int srcX = this.character.getPathPosition().getX().get();
            int srcY = this.character.getPathPosition().getY().get();

            if (this.character.getPathPosition().getX().get() == b.getX() && this.character.getPathPosition().getY().get() == b.getY()) {
                if (b instanceof Village) {
                    Village v = (Village) b;
                    v.increaseHp(this.character);
                }

                if (b instanceof Barracks) {
                    Barracks ba = (Barracks) b;
                    ba.produceAlly(this);
                }
            }

            if (b instanceof Campfire) {
                Campfire c = (Campfire) b;
                int distance = (int)Math.sqrt(Math.pow(destX - srcX,2) + Math.pow(destY - srcY , 2));
                if (distance <= c.getcampRadius()) {
                    this.character.setDamage(this.character.getDamage() * 2);
                } else {
                    this.character.setDamage(100);
                }
            }

        }
    }

    public void enemyStepOnBuilding() {
        List<Building> toRemove = new ArrayList<Building>();
        for (Building b : this.buildings) {
            if (b instanceof Tower) {
                Tower t = (Tower) b;
                t.attack(this);
            }

            if (b instanceof Trap) {
                Trap tr = (Trap) b;
                tr.exertDamage(this, toRemove);
            }
        }

        for (Building b : toRemove) {
            this.buildings.remove(b);
            b.destroy();
        }

    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    /*public Card loadCard(String type) {
        Card newCard = null;
        checkCardEntity();
        switch (type) {
            case "ZombiePit":
                newCard = new ZombiePitCard("ZombiePit", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "Village":
                newCard = new VillageCard("Village", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "Trap":
                newCard = new TrapCard("Trap", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "Tower":
                newCard = new TowerCard("Tower", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "Barracks":
                newCard = new BarracksCard("Barracks", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "Campfire":
                newCard = new CampfireCard("Campfire", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "VampireCastle":
                newCard = new VampireCastleCard("VampireCastleBuilding", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
            default: return null;
        }
        cardEntities.add(newCard);
        return newCard;
    }*/


    public VampireCastleCard loadVampireCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // TODO = give some cash/experience/item rewards for the discarding of the oldest card
            removeCard(0);
        }
        VampireCastleCard vampireCastleCard = new VampireCastleCard("VampireCastleBuilding", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(vampireCastleCard);
        return vampireCastleCard;
    }

    public CampfireCard loadCampfireCard() {
        checkCardEntity();
        CampfireCard campfireCard = new CampfireCard("Campfire", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(campfireCard);
        return campfireCard;
    }


    public TowerCard loadTowerCard() {
        checkCardEntity();
        TowerCard towerCard = new TowerCard("Tower", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(towerCard);
        return towerCard;
    }

    public TrapCard loadTrapCard() {
        checkCardEntity();
        TrapCard trapCard = new TrapCard("Trap", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(trapCard);
        return trapCard;
    }

    public BarracksCard loadBarracksCard() {
        checkCardEntity();
        BarracksCard barracksCard = new BarracksCard("Barracks", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(barracksCard);
        return barracksCard;
    }


    public VillageCard loadVillageCard() {
        checkCardEntity();
        VillageCard villageCard = new VillageCard("Village", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(villageCard);
        return villageCard;
    }

    public ZombiePitCard loadZombiePitCard() {
        checkCardEntity();
        ZombiePitCard zombiePitCard = new ZombiePitCard("ZombiePit", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(zombiePitCard);
        return zombiePitCard;
    }



    public Card generateCard() {
        int totalCards = 7;
        Random rand = new Random();
        int result = rand.nextInt(1000) % totalCards;

        /*switch (result) {
            case 0: return loadCard("VampireCastleCard");
            case 1: return loadCard("CampfireCard");
            case 2: return loadCard("TowerCard");
            case 3: return loadCard("TrapCard");
            case 4: return loadCard("VillageCard");
            case 5: return loadCard("ZombiePitCard");
            case 6: return loadCard("BarracksCard");
            default: return null;
        }*/

        switch (result) {
            case 0: return loadVampireCard();
            case 1: return loadCampfireCard();
            case 2: return loadTowerCard();
            case 3: return loadTrapCard();
            case 4: return loadBarracksCard();
            case 5: return loadVillageCard();
            case 6: return loadZombiePitCard();
            default: return null;
        }

    }



    public void checkCardEntity () {
        if (cardEntities.size() >= getWidth()){
            // give some cash/experience/item rewards for the discarding of the oldest card
            Random rand = new Random();
            int result = rand.nextInt(10) % 3;
            switch (result) {
                case 0: addGold(rand.nextInt(5));
                case 1: addExperience(rand.nextInt(5));
                case 2: generateItem();
            }

            removeCard(0);
        }
    }


    public boolean checkPathTile(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Pair<Integer, Integer> position = new Pair<>(x.get(), y.get());
        for (Pair<Integer, Integer> pos : orderedPath) {
            if (pos.equals(position)) return true;
        }
        return false;
    }


    public boolean checkAdjacentToPathTile(SimpleIntegerProperty x, SimpleIntegerProperty y) {

        Pair<Integer, Integer> up = new Pair<>(x.get() - 1, y.get());
        Pair<Integer, Integer> down = new Pair<>(x.get() + 1, y.get());
        Pair<Integer, Integer> left = new Pair<>(x.get(), y.get() - 1);
        Pair<Integer, Integer> right = new Pair<>(x.get(), y.get() + 1);

        for (Pair<Integer, Integer> pos : orderedPath) {
            if (pos.equals(up) || pos.equals(down) || pos.equals(left) || pos.equals(right)) return true;
        }

        return false;
    }



    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }
        //if (card == null) return null;
        String type = card.getType();

        // now spawn building
        Building newBuilding = createbuilding(type, new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }



}