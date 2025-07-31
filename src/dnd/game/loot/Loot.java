package dnd.game.loot;



public abstract class Loot {

    public abstract String getName();

    public abstract int getAttack();

    public abstract int getHeal();

    public abstract void setHeal(int health);

    public abstract String getDescription();
}
