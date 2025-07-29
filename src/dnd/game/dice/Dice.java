package dnd.game.dice;

import java.util.Random;

public class Dice {
    private int diceValue;

    public int getDiceValue() {
        return diceValue;
    }

    public void rollDice(){
        Random diceRoll = new Random();
        this.diceValue = (diceRoll.nextInt(6)+1);
        System.out.println("You rolled a " + this.diceValue);
    }
}
