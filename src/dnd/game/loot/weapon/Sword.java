package dnd.game.loot.weapon;

import dnd.game.loot.Loot;

/**
 * represents a sword in the game
 */
public class Sword extends Loot {
    private String name = "Sword";
    private int attack = 5;
    private String type = "SwordWeapon";

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
        return "Time to slice! You got a sword";
    }
}
