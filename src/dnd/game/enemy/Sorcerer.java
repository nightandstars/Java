package dnd.game.enemy;

public class Sorcerer extends Enemy {
    String name = "Sorcerer";
    int attack = 2;
    int health = 9;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String getDescription() {
        return "Bipity Bopity, here's the Sorcerer";
    }
}
