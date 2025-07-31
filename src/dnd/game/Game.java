package dnd.game;

import dnd.game.board.Board;

public class Game {

    private int playerPosition = 1;

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void startGame(){
        Board board = new Board();
        board.getBoard();
        board.moveOnBoard();
    }
}
