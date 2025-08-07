package dnd.game.character;

import java.util.Random;

/**
 * Represents a character of type warrior in the game
 */
public class Warrior extends Character{

    /**
     * creates a warrior and assigns a random health/attack, assigns its name, armor class, type for the DB
     * @param name the name of the character chosen by the player
     */
    public Warrior(String name){
        setName(name);
        Random randomNumber = new Random();
        setAttack(5);
        int startingHealth = setMaxHealth(randomNumber.nextInt(5,10)+1);
        setHealth(startingHealth);
        setType("Warrior");
        setMaxAttack(10);
        setArmorClass(12);
    }

    /**
     * creates a warrior according to the data present in the DB
     * @param name the name chosen by the player
     * @param attack the attack saved in the DB
     * @param health the health saved in the DB
     * @param id the id saved in the DB
     */
    public Warrior(String name, int attack, int health, int id){
        setName(name);
        setAttack(attack);
        setMaxHealth(health);
        setHealth(health);
        setType("Warrior");
        setMaxAttack(10);
        setArmorClass(12);
        setId(id);
    }
}
