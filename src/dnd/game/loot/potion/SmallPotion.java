package dnd.game.loot.potion;

import dnd.game.loot.Loot;

public class SmallPotion extends Loot {
    String name = "Small Potion";
    int heal = 2;
    String type = "SmallPotion";

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
        return "A Small Potion, time to heal!";
    }
}
