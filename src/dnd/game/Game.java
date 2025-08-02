package dnd.game;

import dnd.game.board.Board;
import dnd.game.db.MySQLBoard;

import java.util.List;

public class Game {
    Board board = new Board();
    MySQLBoard database = new MySQLBoard();

    private int playerPosition = 1;

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void startNewGame(){
        board.getBoard();
    }

    public void loadPreviousGame(int boardId){
        List<String> previousBoard = database.getBoardById(boardId);
        board.loadBoardFromJson(previousBoard);
        board.moveOnBoard();
    }
}
