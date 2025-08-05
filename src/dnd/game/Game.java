package dnd.game;

import com.google.gson.Gson;
import dnd.game.board.Board;
import dnd.game.board.cell.Cell;
import dnd.game.character.Character;
import dnd.game.db.MySQLBoard;

import java.util.List;

public class Game {
    Board board = new Board();
    MySQLBoard database = new MySQLBoard();

    public void startNewGame(Character chosenCharacter){
        board.getBoard(chosenCharacter);
    }

    public void loadPreviousGame(int boardId, Character chosenCharacter){
       List<Cell> cells = database.loadBoard(boardId);
       board.setBoard(cells);
       board.moveOnBoard(chosenCharacter);
    }
}
