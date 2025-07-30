package dnd.game.enemy;

public class Enemy {
    private String name;
    private int attack;
    private int health;

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setName(String name) {
        this.name = name;
    }
}
