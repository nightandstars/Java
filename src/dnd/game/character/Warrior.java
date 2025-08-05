package dnd.game.character;

import java.util.Random;

public class Warrior extends Character{

    /**
     * creates a warrior with a set name, attack and health
     * @param name the name of the character
     */
    public Warrior(String name){
        setName(name);
        Random randomNumber = new Random();
        setAttack(randomNumber.nextInt(5,10)+1);
        int startingHealth = setMaxHealth(randomNumber.nextInt(5,10)+1);
        setHealth(startingHealth);
        setType("Warrior");
        setMaxAttack(10);
        setArmorClass(12);
    }

    public Warrior(String name, int attack, int health){
        setName(name);
        setAttack(attack);
        setMaxHealth(10);
        setHealth(health);
        setType("Warrior");
        setMaxAttack(10);
        setArmorClass(12);
    }
}
