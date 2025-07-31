package dnd.game.board;

import dnd.game.Game;
import dnd.game.Menu;
import dnd.game.board.cell.Cell;
import dnd.game.board.cell.EnemyCell;
import dnd.game.board.cell.LootCell;
import dnd.game.dice.Dice;
import dnd.game.enemy.Dragon;
import dnd.game.enemy.Goblin;
import dnd.game.enemy.Sorcerer;
import dnd.game.exception.OutOfBoardException;
import dnd.game.board.cell.EmptyCell;
import dnd.game.loot.potion.LargePotion;
import dnd.game.loot.potion.SmallPotion;
import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Lightning;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the board on which the game will be played
 */
public class Board {

    private ArrayList<Cell> board = new ArrayList<>(64);

    public void getBoard() {
        board = this.createBoard();
    }

    /**
     * Creates a "board" (arraylist) with 64 cells
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
                throw new OutOfBoardException();
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
