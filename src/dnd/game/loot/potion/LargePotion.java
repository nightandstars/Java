package dnd.game.loot.potion;

import dnd.game.loot.Loot;

public class LargePotion extends Loot {
    String name = "Large Potion";
    int heal = 5;
    String type = "LargePotion";

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHeal() {
        return heal;
    }

    @Override
    public void setHeal(int health) {
        this.heal = health;
    }

    @Override
    public String getDescription() {
        return "A Large Potion, time to heal!";
    }
}
