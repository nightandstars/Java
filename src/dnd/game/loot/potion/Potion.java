package dnd.game.loot.potion;

import dnd.game.loot.Loot;

public abstract class Potion extends Loot {
    private String name;
    private int heal;

    public String getName() {
        return name;
    }

    public int getHeal() {
        return heal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}
