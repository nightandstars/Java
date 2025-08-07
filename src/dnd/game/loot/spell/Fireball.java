package dnd.game.loot.spell;

import dnd.game.loot.Loot;

/**
 * Represents a fireball spell in the game
 */
public class Fireball extends Spell {

    public Fireball(){
        super("Fireball", 7, "Burn Baby Burn! It's Fireball time", "Fireball Spell, effect: +" + 7 + " attack", 0, "Fireball");
    }

}
