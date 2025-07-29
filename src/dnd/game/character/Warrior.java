package dnd.game.character;

import java.util.Random;

public class Warrior extends Character{

    public Warrior(String name){
        setName(name);
        Random randomNumber = new Random();
        setAttack(randomNumber.nextInt(5,10)+1);
        setHealth(randomNumber.nextInt(5,10)+1);
    }
}
