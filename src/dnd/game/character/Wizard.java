package dnd.game.character;

import java.util.Random;

/**
 * Represents a wizard in the game
 */
public class Wizard extends Character{

    /**
     * creates a wizard with random health and attack, sets armor class, type for the DB
     * @param name name chosen by the player
     */
    public Wizard(String name){
        setName(name);
        Random randomNumber = new Random();
        setAttack(8);
        int startingHealth = setMaxHealth(randomNumber.nextInt(10,15)+1);
        setHealth(startingHealth);
        setType("Wizard");
        setMaxAttack(15);
        setArmorClass(10);
        setInventory(new Inventory());
    }

    /**
     * creates a wizard based on stats saved in the DB
     * @param name name chosen by the player
     * @param attack attack saved in the DB
     * @param health health saved in the DB
     * @param id the id saved in the DB
     */
    public Wizard(String name, int attack, int health, int id){
        setName(name);
        setAttack(attack);
        setMaxHealth(health);
        setHealth(health);
        setType("Wizard");
        setMaxAttack(15);
        setArmorClass(10);
        setId(id);
    }
}
