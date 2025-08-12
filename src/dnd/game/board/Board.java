package dnd.game.board;

import dnd.game.Menu;
import dnd.game.board.cell.*;
import dnd.game.character.Character;
import dnd.game.db.MySQLBoard;
import dnd.game.db.MySQLHero;
import dnd.game.dice.Dice;
import dnd.game.enemy.*;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.*;
import dnd.game.loot.spell.*;
import dnd.game.loot.weapon.*;
import dnd.game.npc.Arcanist;
import dnd.game.npc.Blacksmith;
import dnd.game.npc.Merchant;
import dnd.game.npc.Npc;

import java.util.*;

/**
 * Represents the board on which the game will be played
 */
public class Board implements Dice {

    private List<Cell> board = new ArrayList<>(64);
    private MySQLBoard databaseBoard = new MySQLBoard();
    private int playerPosition = 0;
    private transient Scanner scanner = null;
    private int boardId = 0;
    private MySQLHero databaseHero = null;

    /**
     * Creates a new board and saves it to the DB, starts character's moves
     * @param chosenCharacter character that will play on the board
     */
    public void getBoard(Character chosenCharacter) {
        List<Cell> newBoard = createBoard();
        int boardId = databaseBoard.createBoard(newBoard);
        this.boardId = boardId;
        Menu.showMessage("Enjoy your game! Your board ID is: " + boardId);
        Menu.showMessage("Keep this somewhere to load your board in future games");
        board = newBoard;
        moveOnBoard(chosenCharacter);
    }

    public void setBoard(List<Cell> board){
        this.board = board;
    }
    public void setScanner(Scanner scanner){ this.scanner = scanner;}

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    /**
     * Creates a board (list of cells) with 64 cells, each containing at random an empty cell, an enemy or loot, board has a fixed number of max enemies/loot
     * @return list with 64 indexes and values from 1-64 each representing a cell
     */
    private List<Cell> createBoard() {
        Random randomType = new Random();
        final int MAX_ENEMIES = 38;
        final int MAX_LOOT = 38;
        final int MAX_NPC = 6;
        final int MAX_CELLS = 101; //always +1 than board size desired
        int enemiesAdded = 0;
        int lootAdded = 0;
        int npcAdded = 0;
        for (int i = 1; i < MAX_CELLS; i++) {
            int enemyType = randomType.nextInt(5);
            int lootType = randomType.nextInt(8);
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
                    case 3:
                        board.add(new EnemyCell(new Orc()));
                        break;
                    case 4:
                        board.add(new EnemyCell(new EvilSpirit()));
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
                     case 6:
                         board.add(new LootCell(new Bow()));
                         break;
                     case 7:
                         board.add(new LootCell(new Invisibility()));
                         break;
                 }
                 lootAdded++;
             }else if (npcAdded < MAX_NPC){
                npcAdded += addNpcs(new Merchant());
                npcAdded += addNpcs(new Blacksmith());
                npcAdded += addNpcs(new Arcanist());
                i += (npcAdded -1);
            }else{
                board.add(new EmptyCell());
            }
            }
            Collections.shuffle(board);
            return board;
        }

    public int addNpcs( Npc npc){
        for(int i = 0; i < 2; i++){
            board.add(new NpcCell(npc));
        }
        return 2;
    }

    /**
     * Moves the player on the board according to the result of a random D6, if player position >= 64, game is won, otherwise player keeps moving, triggers an interaction on each cell that the player lands on
     */
    public void moveOnBoard(Character chosenCharacter){
        Menu endMenu = new Menu();
        while(playerPosition < board.size()) {
            int moveUp = rollD6();
            playerPosition += moveUp;
            if(playerPosition >= board.size()) {
                playerPosition = board.size();
                Menu.showMessage("You have won the game!");
                databaseBoard.deleteBoard(boardId);
                databaseHero.updateHero(chosenCharacter);
                endMenu.endOfGameChoice();
            }
            else {
                Cell currentPosition = board.get(playerPosition);
                currentPosition.getDescription();
                Menu.showMessage("You are on cell " + playerPosition);
                currentPosition.interact(chosenCharacter, playerPosition, this);
                Menu.showMessage("Press ENTER to continue");
                scanner.nextLine();
            }
        }
    }

    /**
     * Triggers when player runs from a fight, makes it go back a certain number of cells
     * @param position the number of cells to move back (result of a D6)
     */
    public void goBackOnBoard(int position){
        playerPosition -= position;
        if(playerPosition <= 0){
            playerPosition = 1;
        }
    }

    /**
     * Result of a random D6
     * @return dice value
     */
    @Override
    public int rollD6() {
        Random diceRoll = new Random();
        int diceValue = diceRoll.nextInt(1,7);
        System.out.println("You rolled a " + diceValue);
        return diceValue;
    }

    /**
     * Result of a random D20
     * @return dice value
     */
    @Override
    public int rollD20() {
        Random diceRoll = new Random();
        int diceValue = diceRoll.nextInt(1,21);
        System.out.println("You rolled a " + diceValue);
        return diceValue;
    }

    public void replaceCell(int position){
        board.set(position, new EmptyCell());
    }

    public MySQLBoard getDatabaseBoard(){
        return databaseBoard;
    }

    public int getBoardId(){
        return boardId;
    }

    public List<Cell> getBoard() {
        return board;
    }

    public void setDatabaseHero(MySQLHero databaseHero) {
        this.databaseHero = databaseHero;
    }
}
