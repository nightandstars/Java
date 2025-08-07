package dnd.game.character;
import dnd.game.Menu;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.Potion;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the character that is being played and its stats
 */
public abstract class Character {
    private String name;
    private int health;
    private int attack;
    private List<Loot> inventory = new ArrayList<>(4) ;
    private String type;
    private int maxHealth;
    private int id;
    private int maxAttack;
    private int armorClass;
    private Menu menu = new Menu();

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

    public void showInventory() {
        for(Loot loot : inventory){
            Menu.showMessage("You have in your inventory: ");
            Menu.showMessage(loot.getInventoryDescription());
        }
    }

    public List<Loot> getInventory(){
        return inventory;
    }

    public void setInventory(List<Loot> inventory) {
        this.inventory = inventory;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * When the character finds a potion checks their health, if max nothing happens, else heals according to the potion's stats
     */
    public boolean heal(Potion loot) {
        boolean isPickedUp = false;
        if (health < maxHealth) {
            int newHealth = loot.getHeal();
            if((this.health + newHealth) > maxHealth){
                this.health = maxHealth;
                Menu.showMessage("You are now full health");
            }else{
                health += newHealth;
                Menu.showMessage("You now have " + health + " health");
            }
            isPickedUp = true;
        } else if(countPotionsInInventory() < 2) {
            addInventory(loot);
            Menu.showMessage("The potion has been added to your inventory");
        }else {
                getPotionsInInventory();
                int choice = menu.choosePickupOrDrop();
                if(choice ==1){
                    int index = menu.itemToReplace();
                    inventory.set(index ,loot);
                    isPickedUp = true;
                }
            }
        return isPickedUp;
    }

    /**
     * When the character finds a weapon/spell checks their attack, if max nothing happens, else upgrades their attack according to the weapon's/spell's stats
     */
//    public void upgradeAttack(){
//        int newAttack = inventory.getAttack();
//        if((attack + newAttack) > maxAttack){
//            attack = maxAttack;
//            Menu.showMessage("You're already too powerful");
//        }else{
//            Menu.showMessage("Your previous attack was " + attack);
//            attack += newAttack;
//            Menu.showMessage("Your new attack is " + attack);
//        }
//    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addInventory(Loot loot) {
        inventory.add(loot);
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

    private int countPotionsInInventory(){
        return (int) inventory.stream().filter(loot -> loot instanceof Potion).count();
    }

    public void getPotionsInInventory(){
        Menu.showMessage("Here are the potions in your inventory:");
        for(Loot loot : inventory){
            if(loot instanceof Potion){
                Menu.showMessage("[" + inventory.indexOf(loot) + "] " + loot.getInventoryDescription());
            }
        }
    }


}
