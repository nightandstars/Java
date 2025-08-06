package dnd.game.loot;


/**
 * Represents loot present in the game, each loot has a type that is needed for the DB and is usually equal to the class name of the object it represents
 */
public abstract class Loot {

    public abstract String getName();

    public abstract int getAttack();

    public abstract int getHeal();

    public abstract void setHeal(int health);

    public abstract String getDescription();
}
