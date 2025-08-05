package dnd.game.board;

import dnd.game.Menu;
import dnd.game.board.cell.*;
import dnd.game.character.Character;
import dnd.game.db.MySQLBoard;
import dnd.game.dice.Dice;
import dnd.game.enemy.*;
import dnd.game.loot.potion.*;
import dnd.game.loot.spell.*;
import dnd.game.loot.weapon.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents the board on which the game will be played
 */
public class Board {

    private List<Cell> board = new ArrayList<>(64);
    MySQLBoard databaseBoard = new MySQLBoard();
    private int playerPosition = 0;

    public void getBoard(Character chosenCharacter) {
        List<Cell> newBoard = createBoard();
        int boardId = databaseBoard.createBoard(newBoard);
        Menu.showMessage("Enjoy your game! Your board ID is: " + boardId);
        Menu.showMessage("Keep this somewhere to load your board in future games");
        board = newBoard;
        moveOnBoard(chosenCharacter);
    }

    public void setBoard(List<Cell> board){
        this.board = board;
    }

    /**
     * Creates a "board" (arraylist) with 64 cells, each containing at random an empty cell, an enemy or loot
     * @return array list with 64 indexes and values from 1-64
     */
    private List<Cell> createBoard() {
        Random randomType = new Random();
        final int MAX_ENEMIES = 24;
        final int MAX_LOOT = 24;
        int enemiesAdded = 0;
        int lootAdded = 0;
        for (int i = 1; i < 65; i++) {
            int enemyType = randomType.nextInt(3);
            int lootType = randomType.nextInt(6);
            if (enemiesAdded < MAX_ENEMIES) {
                switch (enemyType) {
                    case 0:
                        board.add(new EnemyCell(new Goblin()));
                        break;
                    case 1:
                        board.add(new EnemyCell(new Sorcerer()));
                        break;
                    case 2:
                        board.add(new EnemyCell(new Dragon()));
                        break;
                }
                enemiesAdded++;
            } else if(lootAdded < MAX_LOOT) {
                 switch (lootType) {
                     case 0:
                         board.add(new LootCell(new LargePotion()));
                         break;
                     case 1:
                         board.add(new LootCell(new SmallPotion()));
                         break;
                     case 2:
                         board.add(new LootCell(new Fireball()));
                         break;
                     case 3:
                         board.add(new LootCell(new Lightning()));
                         break;
                     case 4:
                         board.add(new LootCell(new Sword()));
                         break;
                     case 5:
                         board.add(new LootCell(new Mace()));
                         break;
                 }
                 lootAdded++;
             }else {
                board.add(new EmptyCell());
            }
        }
            Collections.shuffle(board);
            return board;
        }

    /**
     * Instantiates a die, starts the player on cell 1, rolls the dice and move the player on the board until they reach cell 64 and win the game, prints the current cell that the player is on
     */
    public void moveOnBoard(Character chosenCharacter){
        Dice dice = new Dice();
        Menu endMenu = new Menu();
        while(playerPosition < 64) {
            int moveUp = dice.rollD6();
            playerPosition += moveUp;
            if(playerPosition > 64) {
                playerPosition = 64;
                Menu.showMessage("You have won the game!");
                endMenu.endOfGameChoice();
            } else if (playerPosition == 64) {
                Menu.showMessage("You have won the game!");
                endMenu.endOfGameChoice();
            }else {
                Cell currentPosition = board.get((playerPosition - 1));
                currentPosition.getDescription();
                currentPosition.interact(chosenCharacter, playerPosition);
            }
            Menu.showMessage("You are on cell " + playerPosition);
        }
    }

    public int goBackOnBoard(int position){
        return playerPosition -= position;
    }

}
