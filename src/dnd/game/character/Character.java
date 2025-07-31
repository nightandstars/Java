package dnd.game.character;
import dnd.game.loot.Loot;

/**
 * Represents the main characteristics of a character in the game
 */
public abstract class Character {
    private String name;
    private int health;
    private int attack;
    private Loot equipment;
    private String type;
    private int maxHealth;

    public int getHealth(){
        return health;
    }
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack(){
        return attack;
    }

    public Loot getEquipment() {
        return equipment;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setEquipment(Loot equipment) {
        this.equipment = equipment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return name + " is a " + type + " with " + health + " health and " + attack + " attack";
    }
}
