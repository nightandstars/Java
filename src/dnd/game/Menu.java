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

    private transient Scanner myScanner = new Scanner(System.in);
    private Character chosenCharacter;
    private MySQLHero databaseHero = new MySQLHero(this);

    /**
     * This lets the player create a new character, load an existing one or exit the game
     */
    public void startingMenu() {
        System.out.println("Welcome to DND, pick an option below:");
        System.out.println("1 - Create a New Character");
        System.out.println("2 - Load a Character");
        System.out.println("3 - Exit Game");
        int choice = validateChoice(3);
        switch (choice) {
            case 1:
                chosenCharacterClass();
                break;
            case 2:
                databaseHero.getCharacters();
                System.out.println("What is the number of the Character you wish to load?");
                int characterId = checkIntegerInput();
                databaseHero.loadCharacter(characterId);
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
        int characterType = validateChoice(3);
        String characterName = chosenCharacterName();
        switch (characterType) {
            case 1:
                this.chosenCharacter = new Wizard(characterName);
                databaseHero.createHero(this.chosenCharacter, characterName);
                break;
            case 2:
                this.chosenCharacter = new Warrior(characterName);
                databaseHero.createHero(this.chosenCharacter, characterName);
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
        int choice = validateChoice(5);
        switch (choice) {
            case 1:
                System.out.println(chosenCharacter.toString());
                this.characterInfo();
                break;
            case 2:
                System.out.println("What is the new name of your character?");
                String newName = myScanner.nextLine();
                chosenCharacter.setName(newName);
                databaseHero.editHero(this.chosenCharacter, newName);
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
        System.out.println("Do you want to play on a new board or load one?");
        System.out.println("1 - New board");
        System.out.println("2 - Load a board");
        System.out.println("3 - Go back");
        System.out.println("4 - Exit");
        int choice = validateChoice(4);
        switch (choice) {
            case 1:
                game.getScanner(myScanner);
                game.getDatabaseHero(databaseHero);
                game.startNewGame(this.chosenCharacter);
                break;
            case 2:
                System.out.println("What is the id of the board you wish to load?");
                int boardId = checkIntegerInput();
                game.getScanner(myScanner);
                game.getDatabaseHero(databaseHero);
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
         int choice = validateChoice(2);
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
         return validateChoice(2);
     }

     public static void showMessage(String message){
         System.out.println(message);
     }

     public void setChosenCharacter(Character character){
         this.chosenCharacter = character;
     }

     public MySQLHero getDatabaseHero(){
         return databaseHero;
     }

     public int choosePickupOrDrop(){
         System.out.println("You can't pick up anymore of this kind of item, choose an option:");
         System.out.println("1 - Replace another item");
         System.out.println("2 - Leave it");
         return validateChoice(2);
     }

     public int chooseItemToInteractWith(String interaction){
         System.out.println("Which item do you want to " + interaction + "? (enter the number)");
         return checkIntegerInput();
     }

    public int chooseUseEquipmentDuringFight(){
        System.out.println("Do you wish to use an equipment during the fight?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        return validateChoice(2);
    }

    private int checkIntegerInput(){
         while(true){
            if (myScanner.hasNextInt()){
                int integer = myScanner.nextInt();
                myScanner.nextLine();
                return integer;
            }else{
                showMessage("Please enter a valid number");
                myScanner.next();
            }
         }
    }

    public int buyOrSell(){
        System.out.println("Are you here to buy or sell?");
        System.out.println("1 - Buy");
        System.out.println("2 - Sell");
         return validateChoice(2);
    }

    public void notImplemented(){
        System.out.println("The shop is restocking, come back later");
    }

    /**
     * Checks that the option entered is valid (if 2 options player can't enter 4 or a String)
     * @param maxOptions allowed to answer
     * @return number chosen
     */
    private int validateChoice(int maxOptions){
         int choice;
         do{
             choice = checkIntegerInput();
             if(choice < 1 || choice > maxOptions){
                 showMessage("Please enter a valid option");
             }
         }while (choice < 1 || choice > maxOptions);
         return choice;
    }

}


