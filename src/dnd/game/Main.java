package dnd.game;

import dnd.game.board.Board;
import dnd.game.db.MySQL;

public class Main {
    /**
     * allows to run the game
     * @param args needed to run main
     */
    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        mainMenu.startingMenu();

    }
}
