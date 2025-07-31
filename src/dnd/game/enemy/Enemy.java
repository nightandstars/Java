package dnd.game.enemy;

public abstract class Enemy {

    public abstract String getName();

    public abstract int getAttack();

    public abstract int getHealth();

    public abstract void setHealth(int health);

    public abstract String getDescription();
}
