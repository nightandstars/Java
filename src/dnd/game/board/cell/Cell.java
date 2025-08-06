package dnd.game.board.cell;

import dnd.game.board.Board;
import dnd.game.character.Character;

/**
 * Represents a Cell on the board, where loot and enemies are present
 */
public abstract class Cell{

    /**
     * Gives the description of what is present on a cell, overridden by each child
     */
    public abstract void getDescription();

    /**
     * Handles the interaction with a cell on the board (loot or enemy), overridden by each child
     * @param character the character that is being played
     * @param playerPosition the cell on which the character is
     * @param board the board that is being played on
     */
    public void interact(Character character, int playerPosition, Board board ){};
}
