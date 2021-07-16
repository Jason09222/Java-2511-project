package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

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
    private List<Entity> equippedItems;
    private List<Entity> unequippedInventoryItems;
    private List<BasicItem> unPickedItem;

    // TODO = expand the range of buildings
    private List<VampireCastleBuilding> buildingEntities;
    private List<Building> campfires;
    private List<Building> buildings;

    private List<Ally> allies;

    private int goldOwned;

    private int experience;

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
        equippedItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        goldOwned = 0;
        experience = 0;
        buildings = new ArrayList<>();
        allies = new ArrayList<>();
        campfires = new ArrayList<>();
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
                if (v.checkPathCycle()) {
                    Vampire newVam = v.spawnVampire(this);
                    spawningEnemies.add(newVam);
                }
            }

            if (b instanceof ZombiePit) {
                ZombiePit z = (ZombiePit) b;
                if (z.checkPathCycle()) z.spawnZombie(this);
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
        Pair<Integer, Integer> pos1 = possiblyGetBasicItemSpawnPosition();
        Pair<Integer, Integer> pos2 = possiblyGetBasicItemSpawnPosition();
        List<BasicItem> spawningItems = new ArrayList<>();
        if (pos1 != null && pos2 != null) {
            int indexInPath1 = orderedPath.indexOf(pos1);
            int indexInPath2 = orderedPath.indexOf(pos2);
            PathPosition newPathPosition1 = new PathPosition(indexInPath1, orderedPath);
            PathPosition newPathPosition2 = new PathPosition(indexInPath2, orderedPath);
            BasicItem gold = new Gold(newPathPosition1.getX(), newPathPosition1.getY());
            BasicItem healthPotion = new HealthPotion(newPathPosition2.getX(), newPathPosition2.getY());
            unPickedItem.add(gold);
            spawningItems.add(gold);
            unPickedItem.add(healthPotion);
            spawningItems.add(healthPotion);
        }
        return spawningItems;
    }

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



    public Card generateCard() {
        int totalCards = 7;
        Random rand = new Random();
        int result = rand.nextInt(1000) % totalCards;

        switch (result) {
            case 0: return loadCard("VampireCastleCard");
            case 1: return loadCard("CampfireCard");
            case 2: return loadCard("TowerCard");
            case 3: return loadCard("TrapCard");
            case 4: return loadCard("VillageCard");
            case 5: return loadCard("ZombiePitCard");
            case 6: return loadCard("BarracksCard");
            default: return null;
        }

    }

    public void generateItem() {
        BasicItem reward = null;
        int totalRewards = 8;
        Random rand = new Random();
        int result = rand.nextInt(1000) % totalRewards;

        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        SimpleIntegerProperty x = new SimpleIntegerProperty(firstAvailableSlot.getValue0());
        SimpleIntegerProperty y = new SimpleIntegerProperty(firstAvailableSlot.getValue1());
        switch (result) {
            case 1:
                reward = new Armour(x, y);
                break;
            case 2:
                reward = new HealthPotion(x, y);
                break;
            case 3:
                reward = new Helmet(x, y);
                break;
            case 4:
                reward = new Shield(x, y);
                break;
            case 5:
                reward = new Staff(x, y);
                break;
            case 6:
                reward = new Stake(x, y);
                break;
            case 7:
                reward = new Sword(x, y);
                break;
            default:
                return;
        }
        addUnequippedInventory(reward);

        return;
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

    public BasicItem addUnequippedItem(String type) {
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
            case "Sword":
                item = new Sword(x, y);
                break;
            case "Helmet":
                item = new Helmet(x, y);
                break;
            case "Armour":
                item = new Armour(x, y);
                break;
            case "Shield":
                item = new Shield(x, y);
                break;
            case "HealthPotion":
                item = new HealthPotion(x, y);
                break;
            default:
                item = new BasicItem(x, y, null);
                break;
        }
        unequippedInventoryItems.add(item);
        return item;
    }

    /**
     * moves an "item" from unequippedInventory into equippedInventory
     *
     * @param item to be equipped
     *
     */

    public void equipItem(BasicItem item) {
        unequippedInventoryItems.remove(item);
        switch (item.getType()) {
            case "Sword":
                if (equippedItems.get(0) != null) {
                    unequippedInventoryItems.add(equippedItems.get(0));
                }
                equippedItems.set(0, item);

            case "Helmet":
                if (equippedItems.get(1) != null) {
                    unequippedInventoryItems.add(equippedItems.get(1));
                }
                equippedItems.set(1, item);
            case "Armour":
                if (equippedItems.get(2) != null) {
                    unequippedInventoryItems.add(equippedItems.get(2));
                }
                equippedItems.set(2, item);
            case "Shield":
                if (equippedItems.get(3) != null) {
                    unequippedInventoryItems.add(equippedItems.get(3));
                }
                equippedItems.set(3, item);
            default:
                break;
        }
    }

    public void unEquipItem(BasicItem item) {
        unequippedInventoryItems.add(item);
        equippedItems.set(equippedItems.indexOf(item), null);
    }

    /*
     * public void equipSword(Sword sword) { if (equippedItems.get(0) != null) {
     * unequippedInventoryItems.add(equippedItems.get(0)); } equippedItems.set(0,
     * sword); }
     *
     * public void equipHelmet(Helmet helmet) { if (equippedItems.get(1) != null) {
     * unequippedInventoryItems.add(equippedItems.get(1)); } equippedItems.set(1,
     * helmet); }
     *
     * public void equipArmour(Armour armour) { if (equippedItems.get(2) != null) {
     * unequippedInventoryItems.add(equippedItems.get(2)); } equippedItems.set(2,
     * armour); }
     *
     * public void equipShield(Shield shield) { if (equippedItems.get(3) != null) {
     * unequippedInventoryItems.add(equippedItems.get(3)); } equippedItems.set(3,
     * shield); }
     */

    /**
     * remove an item by x,y coordinates
     *
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything
     * immediately
     */
    public void runTickMoves() {

        if (!character.getInBattle()) {
            character.moveDownPath();
        }
        moveBasicEnemies();
        updatePathCycle();
        charactersStepOnBuilding();
        enemyStepOnBuilding();
    }

    public void updatePathCycle() {
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
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Entity e : unequippedInventoryItems) {
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
                Building nearestCamp = this.getShortestCampire(e);
                if (e.getType().equals("Vampire") && nearestCamp != null) {
                    if (e.getDistance(nearestCamp.getX(), nearestCamp.getY()) <= 2) {
                        e.moveDownPath();
                        continue;
                    } else if (e.getDistance(nearestCamp.getX(), nearestCamp.getY()) == 3) {
                        if (e.getLastDirec().equals("Up")) {
                            e.moveDownPath();
                            e.setLastDirec("Down");
                        } else {
                            e.moveUpPath();
                            e.setLastDirec("Up");
                        }
                        continue;
                    }
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
            if (enemy.getType().equals("Slug")) {
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

    /**
     * get a randomly generated position which could be used to spawn an item
     *
     * @return null if random choice is that wont be spawning an enemy or it isn't
     *         possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicItemSpawnPosition() {

        // has a chance spawning a basic item on a tile the character isn't on or
        // immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2);
        if ((choice == 0) && (enemies.size() < 2)) {
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


    public int getGold() {
        return this.goldOwned;
    }

    public void addGold(int numGained) {
        this.goldOwned += numGained;
    }

    public void spendGold(int numLost) {
        this.goldOwned += numLost;
    }

    public int getExperience() {
        return this.experience;
    }

    public void addExperience(int numGained) {
        this.experience += numGained;
    }

    public Building getShortestCampire(BasicEnemy e) {
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

    public void addUnequippedInventory(StaticEntity item) {
        if (this.unequippedInventoryItems.size() == 15) {
            this.unequippedInventoryItems.remove(0);
            this.goldOwned += 100;
        }
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
                generateItem();
                break;
            case 2:
                StaticEntity healthP = new HealthPotion(x, y);
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
                this.campfires.add(newBuilding);
        }

        this.buildings.add(newBuilding);
        return newBuilding;
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
        for (Building b : this.buildings) {
            if (b instanceof Tower) {
                Tower t = (Tower) b;
                t.attack(this);
            }

            if (b instanceof Trap) {
                Trap tr = (Trap) b;
                tr.exertDamage(this);
            }
        }
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public Card loadCard(String type) {
        Card newCard = null;
        checkCardEntity();
        switch (type) {
            case "ZombiePitCard":
                newCard = new ZombiePitCard("ZombiePit", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "VillageCard":
                newCard = new VillageCard("Village", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "TrapCard":
                newCard = new TrapCard("Trap", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "TowerCard":
                newCard = new TowerCard("Tower", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "BarracksCard":
                newCard = new BarracksCard("Barracks", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "CampfireCard":
                newCard = new CampfireCard("Campfire", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
                break;
            case "VampireCastleCard":
                newCard = new VampireCastleCard("VampireCastle", new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
            default: return null;
        }
        cardEntities.add(newCard);
        return newCard;
    }

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