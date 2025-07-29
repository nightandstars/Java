package dnd.game.character;

import java.util.Random;

public class Wizard extends Character{

    public Wizard(String name){
        setName(name);
        Random randomNumber = new Random();
        setAttack(randomNumber.nextInt(8,15)+1);
        setHealth(randomNumber.nextInt(3,6)+1);
    }
}
