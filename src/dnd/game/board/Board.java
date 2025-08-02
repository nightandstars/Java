package dnd.game.board;

import com.google.gson.*;
import dnd.game.Game;
import dnd.game.Menu;
import dnd.game.board.cell.*;
import dnd.game.db.MySQLBoard;
import dnd.game.dice.Dice;
import dnd.game.enemy.*;
import dnd.game.loot.potion.*;
import dnd.game.loot.spell.*;
import dnd.game.loot.weapon.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the board on which the game will be played
 */
public class Board {

    private ArrayList<Cell> board = new ArrayList<>(64);
    MySQLBoard databaseBoard = new MySQLBoard();
    Gson gson = new Gson();

    public void getBoard() {
        board = this.createBoard();
        List<String> jsonBoard = boardToJson();
        databaseBoard.createBoard(jsonBoard);
        moveOnBoard();
    }

    /**
     * Creates a "board" (arraylist) with 64 cells, each containing at random an empty cell, an enemy or loot
     * @return array list with 64 indexes and values from 1-64
     */
    private ArrayList<Cell> createBoard(){
        Random randomType = new Random();
        for(int i = 1; i < 65; i++){
            int type = randomType.nextInt(10);
            switch(type){
                case 0:
                    board.add(new EmptyCell());
                    break;
                case 1:
                    board.add(new EnemyCell(new Goblin()));
                    break;
                case 2:
                    board.add(new EnemyCell(new Sorcerer()));
                    break;
                case 3:
                    board.add(new EnemyCell(new Dragon()));
                    break;
                case 4:
                    board.add(new LootCell(new LargePotion()));
                    break;
                case 5:
                    board.add(new LootCell(new SmallPotion()));
                    break;
                case 6:
                    board.add(new LootCell(new Fireball()));
                    break;
                case 7:
                    board.add(new LootCell(new Lightning()));
                    break;
                case 8:
                    board.add(new LootCell(new Sword()));
                    break;
                case 9:
                    board.add(new LootCell(new Mace()));
                    break;
            }
        }
        return board;
    }

    public List<String> boardToJson(){
        List<String> jsonList = new ArrayList<>();
        for (Cell cell : this.board) {
            String json = gson.toJson(cell);
            jsonList.add(json);
        }
        return jsonList;
    }

    public void loadBoardFromJson(List<String> jsonList){
        ArrayList<Cell> activeBoard = new ArrayList<>();
        for(String json : jsonList){
            Cell cell = databaseBoard.getCellType(json);
            activeBoard.add(cell);
        }
        this.board = activeBoard;
    }

    /**
     * Instantiates a die, starts the player on cell 1, rolls the dice and move the player on the board until they reach cell 64 and win the game, prints the current cell that the player is on
     */
    public void moveOnBoard(){
        Dice dice = new Dice();
        Game player = new Game();
        int playerPosition = player.getPlayerPosition();
        while(playerPosition < 64) {
            int moveUp = dice.rollDice();
            playerPosition += moveUp;
            if(playerPosition > 64) {
                playerPosition = 64;
            } else if (playerPosition == 64) {
                System.out.println("You are on cell " + playerPosition);
                Menu endMenu = new Menu();
                endMenu.endOfGameChoice();
            }else {
                Cell currentPosition = board.get((playerPosition - 1));
                currentPosition.getDescription();
            }
            System.out.println("You are on cell " + playerPosition);
        }
    }

}
