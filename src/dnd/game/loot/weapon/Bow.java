package dnd.game.loot.weapon;

import dnd.game.loot.Loot;

/**
 * represents a mace in the game
 */
public class Bow extends Weapon {

    public Bow(){
        super("Bow", 4, "An arrow between the eyes, you got a bow", "Bow, effect: +" + 6 + " attack against Dragons and +" +4 + " against other enemies", 6, "Bow");
    }

}
