package dnd.game.dice;

import java.util.Random;

public interface Dice {

    /**
     * Rolls a D6 and get a random number each time
     * @return int value of the die
     */
    int rollD6();

    int rollD20();

}
