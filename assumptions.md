Client requirements does not specify how battles will take place other than the requirement that there will be no human player interaction involved we will assume the following:
1. The player will always attack first once in the battle radius of the enemy.
2. The enemy will attack in order of strength: vampire first followed by zombie then slug
3. Any allies will attack after the player and the enemies have attacked.
4. Process repeats until either the player loses all health or the enemies are defeated.
5. Players will not be able to move to the next tile until all enemies in the current battle are defeated.

Potions can be used on any tile as long as the player is not engaged in battle with an enemy. 

Client requirement does not specify how the enemies will move along the path, only referring to each enemy as having a different movement pattern. As such we will assume the following (assumptions for enemy movement will change as well plan out our design):
1. Slugs will move back and forth between their spawn tile and an adjacent tile.
2. Zombies will move 1 tile anticlockwise every turn.
3. Vampires will move 1 tile anticlockwise however they will reverse if their next turn will place them into a campfire battle radius.

Players will have 12 slots for equipment

Players will have 13 slots for cards
