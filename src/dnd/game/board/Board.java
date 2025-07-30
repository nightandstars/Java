package dnd.game.board;

import dnd.game.Menu;
import dnd.game.dice.Dice;
import dnd.game.exception.OutOfBoardException;

import java.util.ArrayList;

/**
 * Represents the board on which the game will be played
 */
public class Board {

    private ArrayList<Integer> board = new ArrayList<>(64);

    public void getBoard() {
        board = this.createBoard();
        for(int cell : board){
            System.out.println(cell);
        }
    }

    /**
     * Creates a "board" (arraylist) with 64 cells
     * @return array list with 64 indexes and values from 1-64
     */
    private ArrayList<Integer> createBoard(){
        for(int i = 1; i < 65; i++){
            board.add(i);
        }
        return board;
    }

    /**
     * Instantiates a die, starts the player on cell 1, rolls the dice and move the player on the board until they reach cell 64 and win the game, prints the current cell that the player is on
     */
    public void moveOnBoard(){
        Dice dice = new Dice();
        int currentCell = 1;
        while(currentCell < 64) {
            int moveUp = dice.rollDice();
            currentCell += moveUp;
            if(currentCell > 64) {
                throw new OutOfBoardException();
            } else if (currentCell == 64) {
                System.out.println("You are on cell " + currentCell);
                Menu endMenu = new Menu();
                endMenu.endOfGameChoice();
            }
            System.out.println("You are on cell " + currentCell);
        }
    }

}
