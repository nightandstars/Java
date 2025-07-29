package dnd.game.loot.spells;

public class Spell {
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
