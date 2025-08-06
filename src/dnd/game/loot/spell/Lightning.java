package dnd.game.loot.spell;

import dnd.game.loot.Loot;

/**
 * represents a lightning spell in the game
 */
public class Lightning extends Loot {
    String name = "Lightning";
    int attack = 2;
    String type = "LightningSpell";

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
        return "Call the lightning! Zap";
    }
}
