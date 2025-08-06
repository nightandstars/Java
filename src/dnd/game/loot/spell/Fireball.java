package dnd.game.loot.spell;

import dnd.game.loot.Loot;

/**
 * Represents a fireball spell in the game
 */
public class Fireball extends Loot {
    String name = "Fireball";
    int attack = 7;
    String type = "FireballSpell";

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
        return "Burn Baby Burn! It's Fireball time";
    }
}
