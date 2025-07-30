package dnd.game.dice;

import java.util.Random;

public class Dice {

    //rolls a D6 at random
    public int rollDice(){
        Random diceRoll = new Random();
        int diceValue = (diceRoll.nextInt(6) + 1);
        System.out.println("You rolled a " + diceValue);
        return diceValue;
    }
}
