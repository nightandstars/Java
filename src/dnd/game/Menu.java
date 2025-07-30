package dnd.game;

import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;

import java.util.Scanner;

public class Menu {

    Scanner myScanner = new Scanner(System.in);
    Character chosenCharacter = null;

    //create a character or exits game
    public void startingMenu() {
        System.out.println("Welcome to DND, pick an option below:");
        System.out.println("1 - Create a New Character");
        System.out.println("2 - Exit Game");
        int choice = myScanner.nextInt();

        switch (choice) {
            case 1:
                Character character = this.chosenCharacterClass();
                break;
            case 2:
                this.quitGame();
        }
        this.characterInfo();
    }

    //creates a character based on a class and a chosen name
    private Character chosenCharacterClass() {
        System.out.println("Do you want a Wizard or a Warrior?:");
        System.out.println("1 - Wizard");
        System.out.println("2 - Warrior");
        System.out.println("3 - Exit");
        int characterType = myScanner.nextInt();
        String characterName = this.chosenCharacterName();
        switch (characterType) {
            case 1:
                return chosenCharacter = new Wizard(characterName);
            case 2:
                return chosenCharacter = new Warrior(characterName);
            case 3:
                this.quitGame();
                break;
        }
        return chosenCharacter;
    }

    //allows to see the character info or modify its name
    private void characterInfo() {
        System.out.println("Do you want to:");
        System.out.println("1 - See the character's info?");
        System.out.println("2 - Modify the info?");
        System.out.println("3 - Start the game");
        System.out.println("4 - Exit");
        int choice = myScanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println(chosenCharacter.toString());
                this.characterInfo();
                break;
            case 2:
                myScanner.nextLine();
                System.out.println("What is the new name of your character?");
                String newName = myScanner.nextLine();
                chosenCharacter.setName(newName);
                this.characterInfo();
                break;
            case 3:
                this.startGame();
                break;
            case 4:
                this.quitGame();
        }
    }

    //quits game
    public void quitGame() {
        System.exit(0);
    }

    //defines a character name
    private String chosenCharacterName() {
        myScanner.nextLine();
        System.out.println("What is their name?");
        String characterName = myScanner.nextLine();
        return characterName;
    }

    //starts the game and moves the player on the board
    private void startGame() {
        System.out.println("Do you want to start the game?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        int choice = myScanner.nextInt();
        switch (choice) {
            case 1:
                Board board = new Board();
                board.moveOnBoard();
            case 2:
                this.quitGame();
                break;
        }

    }

    //allows to start a new game or exit
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


