package dnd.game.loot.spell;

import dnd.game.loot.Loot;

/**
 * Represents a fireball spell in the game
 */
public class Invisibility extends Loot {
    String name = "Invisibility";
    int attack = 0;
    String type = "InvisibilitySpell";

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHeal() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Where did you go? You are now invisible";
    }
}
