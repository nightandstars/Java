package dnd.game.dice;

import java.util.Random;

public class Dice {

    /**
     * Rolls a D6 and get a random number each time
     * @return int value of the die
     */
    public int rollD6(){
        Random diceRoll = new Random();
        int diceValue = diceRoll.nextInt(1,7);
        System.out.println("You rolled a " + diceValue);
        return diceValue;
    }

    public int rollD20(){
        Random diceRoll = new Random();
        int diceValue = diceRoll.nextInt(1,21);
        System.out.println("You rolled a " + diceValue);
        return diceValue;
    }

}
