package dnd.game.loot.weapon;

import dnd.game.loot.Loot;

/**
 * represents a mace in the game
 */
public class Bow extends Loot {
    String name = "Bow";
    int attack = 0;
    String type = "BowWeapon";

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
        return "An arrow between the eyes, you got a bow";
    }
}
