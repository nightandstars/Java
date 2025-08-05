package dnd.game.character;

import java.util.Random;

public class Wizard extends Character{

    /**
     * create a wizard based on a set name, attack and health
     * @param name defines the name of the wizard
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
