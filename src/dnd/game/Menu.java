package dnd.game;

import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;

import java.util.Scanner;

public class Menu {

    Scanner myScanner = new Scanner(System.in);
    Character chosenCharacter = null;

    public void startingMenu(){
        System.out.println("Welcome to DND, pick an option below:");
        System.out.println("1 - Create a New Character");
        System.out.println("2 - Exit Game");
        int choice = myScanner.nextInt();

        switch(choice){
            case 1:
                Character character = this.chosenCharacterClass();
                break;
            case 2:
                this.quitGame();
        }
        this.characterInfo();

        }

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

    private void characterInfo(){
        System.out.println("Do you want to:");
        System.out.println("1 - See the character's info?");
        System.out.println("2 - Modify the info?");
        System.out.println("3 - Exit");
        int choice = myScanner.nextInt();
        switch(choice){
            case 1:
                System.out.println(chosenCharacter.toString());
                break;
            case 2:
                myScanner.nextLine();
                System.out.println("What is the new name of your character?");
                String newName = myScanner.nextLine();
                chosenCharacter.setName(newName);
                break;
            case 3:
                this.quitGame();
        }
    }

    public void quitGame(){
        System.exit(0);
    }

    private String chosenCharacterName(){
        myScanner.nextLine();
        System.out.println("What is their name?");
        String characterName = myScanner.nextLine();
        return characterName;
    }
}


