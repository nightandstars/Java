package dnd.game.board;

import dnd.game.Menu;
import dnd.game.dice.Dice;

import java.util.ArrayList;

public class Board {

    private ArrayList<Integer> board = new ArrayList<>(64);

    public void getBoard() {
        board = this.createBoard();
        for(int cell : board){
            System.out.println(cell);
        }
    }

    private ArrayList<Integer> createBoard(){
        for(int i = 1; i < 65; i++){
            board.add(i);
        }
        return board;
    }

    //rolls the dice and moves up on the board until the end of game
    public void moveOnBoard(){
        Dice dice = new Dice();
        int currentCell = 1;
        while(currentCell < 64) {
            int moveUp = dice.rollDice();
            currentCell += moveUp;
            if(currentCell >= 64){
                currentCell = 64;
                System.out.println("You are on cell " + currentCell);
                Menu endMenu = new Menu();
                endMenu.endOfGameChoice();
            }
            System.out.println("You are on cell " + currentCell);

        }
    }

}
