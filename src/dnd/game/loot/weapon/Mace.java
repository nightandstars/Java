package dnd.game.loot.weapon;

import dnd.game.loot.Loot;

/**
 * represents a mace in the game
 */
public class Mace extends Loot {
    String name = "Mace";
    int attack = 3;
    String type = "MaceWeapon";

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
    public void setHeal(int health) {
        int heal = 0;
    }

    @Override
    public String getDescription() {
        return "Boink! This mace is going to hurt";
    }
}
