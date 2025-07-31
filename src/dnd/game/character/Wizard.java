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
        setMaxHealth(randomNumber.nextInt(3,6)+1);
        setType("Wizard");
    }
}
