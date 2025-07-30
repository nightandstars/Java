package dnd.game.loot.weapon;

import dnd.game.loot.Loot;

public abstract class Weapon extends Loot {
    private String name;
    private int attack;

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
