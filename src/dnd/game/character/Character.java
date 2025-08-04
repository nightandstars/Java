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
    private int id;
    private int maxAttack;

    public int getHealth(){
        return health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
        return maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack(){
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void heal(){
        int newHealth = equipment.getHeal();
        if((this.health + newHealth) >= maxHealth){
            this.health = maxHealth;
            System.out.println("You're already full health");
        }else {
            this.health += newHealth;
            System.out.println("You now have " + health + " health");
        }
    }
    public void upgradeAttack(){
        int newAttack = equipment.getAttack();
        if((this.attack + newAttack) >= maxAttack){
            this.attack = maxAttack;
            System.out.println("You're already too powerful");
        }else{
            System.out.println("Your new attack is " + attack);
            this.attack += newAttack;
        }
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

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public String toString(){
        return name + " is a " + type + " with " + health + " health and " + attack + " attack";
    }
}
