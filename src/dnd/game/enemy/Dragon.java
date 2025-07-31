package dnd.game.enemy;

public class Dragon extends Enemy {
    private String name = "Dragon";
    private int attack = 4;
    private int health = 15;

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String getDescription() {
        return "Woosh Woosh, a Dragon appeared over you, take cover!";
    }
}
