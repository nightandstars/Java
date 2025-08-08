package dnd.game.loot.spell;

import dnd.game.loot.Loot;

/**
 * Represents a fireball spell in the game
 */
public class Invisibility extends Spell {

    public Invisibility(){
        super("Invisibility", 5, "Where did you go? You are now invisible", "Invisibility, effect: +" + 8 + " attack against Evil Spirits and +" + 5 + " against other enemies", 8, "Invisibility");
    }

}
