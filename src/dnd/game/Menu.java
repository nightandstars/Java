package dnd.game;

import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;
import dnd.game.db.MySQLHero;

import java.util.Scanner;

/**
 * Represents the different menus to navigate through the game and execute actions depending on a choice
 */
public class Menu {

    Scanner myScanner = new Scanner(System.in);
    Character chosenCharacter = null;
    MySQLHero database = new MySQLHero(this);

    /**
     * This lets the player create a new character, load an existing one or exit the game
     */
    public void startingMenu() {
        System.out.println("Welcome to DND, pick an option below:");
        System.out.println("1 - Create a New Character");
        System.out.println("2 - Load a previous Character");
        System.out.println("3 - Exit Game");
        int choice = myScanner.nextInt();

        switch (choice) {
            case 1:
                chosenCharacterClass();
                break;
            case 2:
                System.out.println("What is the id of the Character you wish to load?");
                int characterId = myScanner.nextInt();
                database.loadCharacter(characterId);
                characterInfo();
                break;
            case 3:
                this.quitGame();
                break;
        }
    }

    /**
     * This lets the player choose a class for their character as well as a name which gets saved to the DB, or exit
     */
    private void chosenCharacterClass() {
        System.out.println("Do you want a Wizard or a Warrior?:");
        System.out.println("1 - Wizard");
        System.out.println("2 - Warrior");
        System.out.println("3 - Exit");
        int characterType = myScanner.nextInt();
        myScanner.nextLine();
        String characterName = chosenCharacterName();
        switch (characterType) {
            case 1:
                this.chosenCharacter = new Wizard(characterName);
                //database.createHero(this.chosenCharacter, characterName);
                break;
            case 2:
                this.chosenCharacter = new Warrior(characterName);
                //database.createHero(this.chosenCharacter, characterName);
                break;
            case 3:
                this.quitGame();
                break;
        }
        characterInfo();
    }

    /**
     * This lets the player see their character's info, modify the character's name, start the game, choose a new class or exit
     */
    private void characterInfo() {
        System.out.println("Do you want to:");
        System.out.println("1 - See the character's info?");
        System.out.println("2 - Modify the info?");
        System.out.println("3 - Start the game");
        System.out.println("4 - Change Class");
        System.out.println("5 - Exit");
        int choice = myScanner.nextInt();
        myScanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println(chosenCharacter.toString());
                this.characterInfo();
                break;
            case 2:
                System.out.println("What is the new name of your character?");
                String newName = myScanner.nextLine();
                chosenCharacter.setName(newName);
                database.editHero(this.chosenCharacter, newName);
                this.characterInfo();
                break;
            case 3:
                this.startGame();
                break;
            case 4:
                this.chosenCharacterClass();
                break;
            case 5:
                this.quitGame();
                break;
        }
    }

    /**
     * Quits the game/terminal
     */
    private void quitGame() {
        System.exit(0);
    }

    /**
     * Lets the player choose a name for their character
     * @return the name of the character
     */
    private String chosenCharacterName() {
        System.out.println("What is their name?");
        return myScanner.nextLine();
    }

    /**
     * Starts the game, loads a previous one (ie previous board), go back to character info menu or exit
     */
    private void startGame() {
        Game game = new Game();
        System.out.println("Do you want to start a new game or load an existing one?");
        System.out.println("1 - Start a new game");
        System.out.println("2 - Load an existing one");
        System.out.println("3 - Go back");
        System.out.println("4 - Exit");
        int choice = myScanner.nextInt();
        myScanner.nextLine();
        switch (choice) {
            case 1:
                game.getScanner(myScanner);
                game.startNewGame(this.chosenCharacter);
                break;
            case 2:
                System.out.println("What is the id of the board you wish to load?");
                int boardId = myScanner.nextInt();
                game.getScanner(myScanner);
                game.loadPreviousGame(boardId, this.chosenCharacter);
            case 3:
                this.characterInfo();
                break;
            case 4:
                this.quitGame();
                break;
        }
    }

    /**
     * Once the game is won or player is dead, allows the player to start a new game or exit
     */
     public void endOfGameChoice(){
         System.out.println("1 - Start a new game");
         System.out.println("2 - Exit");
         int choice = myScanner.nextInt();
         switch (choice) {
             case 1:
                 this.startingMenu();
                 break;
             case 2:
                 this.quitGame();
                 break;
         }
     }

    /**
     * Allows the player to keep fighting an enemy or run
     * @return choice between running or keep fighting
     */
     public int fightingMenu(){
         System.out.println("Do you want to keep fighting or do you want to run?");
         System.out.println("1 - Keep fighting");
         System.out.println("2 - Run");
         int choice = myScanner.nextInt();
         return choice;
     }

     public static void showMessage(String message){
         System.out.println(message);
     }

     public void setChosenCharacter(Character character){
         this.chosenCharacter = character;
     }
}


