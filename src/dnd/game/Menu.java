package dnd.game;


import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;

import java.util.Scanner;

/**
 * Represents the different menus to navigate through the game and play it
 */
public class Menu {

    Scanner myScanner = new Scanner(System.in);
    Character chosenCharacter = null;

    /**
     * This lets the player create a new character or exit the game and proceeds to chosenCharacterClass()
     */
    public void startingMenu() {
        System.out.println("Welcome to DND, pick an option below:");
        System.out.println("1 - Create a New Character");
        System.out.println("2 - Exit Game");
        int choice = myScanner.nextInt();

        switch (choice) {
            case 1:
                chosenCharacterClass();
                break;
            case 2:
                this.quitGame();
                break;
        }
    }

    /**
     * This lets the player choose a class for their character as well as a name, or exit, and proceeds to instantiate said character
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
                break;
            case 2:
                this.chosenCharacter = new Warrior(characterName);
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
        String characterName = myScanner.nextLine();
        return characterName;
    }

    /**
     * Starts the game, initializes the board and moves the character on the board until end of game
     */
    private void startGame() {
        System.out.println("Do you want to start the game?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        System.out.println("3 - Exit");
        int choice = myScanner.nextInt();
        myScanner.nextLine();
        switch (choice) {
            case 1:
                Game game = new Game();
                game.startGame();
                break;
            case 2:
                this.characterInfo();
                break;
            case 3:
                this.quitGame();
                break;
        }

    }

    /**
     * Once the game is won, allows the player to start a new game or exit
     */
     public void endOfGameChoice(){
         System.out.println("Congrats, you have won the game!");
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
}


