package dnd.game.character;
import dnd.game.Menu;
import dnd.game.loot.Loot;

/**
 * Represents the character that is being played and its stats
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
    private int armorClass;

    public int getHealth(){
        return health;
    }

    public int getArmorClass(){
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
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

    /**
     * When the character finds a potion checks their health, if max nothing happens, else heals according to the potion's stats
     */
    public void heal(){
        int newHealth = equipment.getHeal();
        if((this.health + newHealth) >= maxHealth){
            this.health = maxHealth;
            Menu.showMessage("You're already full health");
        }else {
            this.health += newHealth;
            Menu.showMessage("You now have " + health + " health");
        }
    }

    /**
     * When the character finds a weapon/spell checks their attack, if max nothing happens, else upgrades their attack according to the weapon's/spell's stats
     */
    public void upgradeAttack(){
        int newAttack = equipment.getAttack();
        if((this.attack + newAttack) >= maxAttack){
            this.attack = maxAttack;
            Menu.showMessage("You're already too powerful");
        }else{
            Menu.showMessage("Your new attack is " + attack);
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

    /**
     * Allows the player to see the basic stats of their character
     * @return String with corresponding stats
     */
    public String toString(){
        return name + " is a " + type + " with " + health + " health and " + attack + " attack";
    }
}
