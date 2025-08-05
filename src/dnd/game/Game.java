package dnd.game;

import dnd.game.board.Board;
import dnd.game.board.cell.Cell;
import dnd.game.character.Character;
import dnd.game.db.MySQLBoard;

import java.util.List;
import java.util.Scanner;

public class Game {
    Board board = new Board();
    MySQLBoard database = new MySQLBoard();
    Scanner scanner = null;

    public void startNewGame(Character chosenCharacter){
        board.setScanner(scanner);
        board.getBoard(chosenCharacter);
    }

    public void loadPreviousGame(int boardId, Character chosenCharacter){
       List<Cell> cells = database.loadBoard(boardId);
       board.setScanner(scanner);
       board.setBoard(cells);
       board.moveOnBoard(chosenCharacter);
    }

    public void getScanner(Scanner scanner){
        this.scanner = scanner;
    }
}
