package dnd.game.enemy;

public class Goblin extends Enemy {
    String name = "Goblin";
    int attack = 1;
    int health = 6;

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Oh no! An ugly Goblin appeared";
    }
}
