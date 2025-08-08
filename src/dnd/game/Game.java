package dnd.game;

import dnd.game.board.Board;
import dnd.game.board.cell.Cell;
import dnd.game.character.Character;
import dnd.game.db.MySQLBoard;
import dnd.game.db.MySQLHero;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the basic mechanic of starting a game
 */
public class Game {
    private Board board = new Board();
    private MySQLBoard database = new MySQLBoard();
    private transient Scanner scanner = null;
    private MySQLHero databaseHero = null;

    /**
     * Starts a new game which means creating a new board
     * @param chosenCharacter
     */
    public void startNewGame(Character chosenCharacter){
        board.setScanner(scanner);
        board.setDatabaseHero(databaseHero);
        board.getBoard(chosenCharacter);
    }

    /**
     * Starts a previous game which means loading a previous board from the DB
     * @param boardId of the board to be loaded
     * @param chosenCharacter character to be played
     */
    public void loadPreviousGame(int boardId, Character chosenCharacter){
       List<Cell> cells = database.loadBoard(boardId);
       board.setDatabaseHero(databaseHero);
       board.setScanner(scanner);
       board.setBoard(cells);
       board.setBoardId(boardId);
       board.moveOnBoard(chosenCharacter);
    }

    /**
     * gets the scanner instantiated inside the menu and passes it to the board
     * @param scanner instance present in the Menu class
     */
    public void getScanner(Scanner scanner){
        this.scanner = scanner;
    }

    public void getDatabaseHero(MySQLHero database){
        this.databaseHero = database;
    }
}
