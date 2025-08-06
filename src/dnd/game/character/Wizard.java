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
        setAttack(randomNumber.nextInt(8,15)+1);
        int startingHealth = setMaxHealth(randomNumber.nextInt(3,6)+1);
        setHealth(startingHealth);
        setType("Wizard");
        setMaxAttack(15);
        setArmorClass(10);
    }

    /**
     * creates a wizard based on stats saved in the DB
     * @param name name chosen by the player
     * @param attack attack saved in the DB
     * @param health health saved in the DB
     */
    public Wizard(String name, int attack, int health){
        setName(name);
        setAttack(attack);
        setMaxHealth(6);
        setHealth(health);
        setType("Wizard");
        setMaxAttack(15);
        setArmorClass(10);
    }
}
