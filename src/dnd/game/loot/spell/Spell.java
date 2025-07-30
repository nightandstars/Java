package dnd.game.loot.spell;

import dnd.game.loot.Loot;

public abstract class Spell extends Loot {
    private String name;
    private int attack;

    public int getAttack() {
        return attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
