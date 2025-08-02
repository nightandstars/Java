package dnd.game.loot.spell;

import dnd.game.loot.Loot;

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
    public void setHeal(int health) {
        int heal = 0;
    }

    @Override
    public String getDescription() {
        return "Burn Baby Burn! It's Fireball time";
    }
}
