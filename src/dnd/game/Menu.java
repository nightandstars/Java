package dnd.game;

import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;

import java.util.Scanner;

public class Menu {

    public void startingMenu(){
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Welcome to DND, pick an option below:");
        System.out.println("1 - Create a New Character");
        System.out.println("2 - Exit Game");

        int choice = myScanner.nextInt();
        Character chosenCharacter = null;

        switch(choice){
            case 1:
                System.out.println("Do you want a Wizard or a Warrior?:");
                System.out.println("1 - Wizard");
                System.out.println("2 - Warrior");
                System.out.println("3 - Exit");
                int characterType = myScanner.nextInt();
                System.out.println("What is their name?");
                String characterName = myScanner.nextLine();
                switch (characterType){
                    case 1:
                         chosenCharacter = this.newCharacter("Wizard", characterName);
                        break;
                    case 2:
                        chosenCharacter = this.newCharacter("Warrior", characterName);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
                break;
            case 2:
                System.exit(0);
        }

        System.out.println("Do you want to:");
        System.out.println("1 - See the character's info?");
        System.out.println("2 - Modify the info?");
        System.out.println("3 - Exit");
        int choice2 = myScanner.nextInt();
        switch(choice2){
            case 1:
                System.out.println(chosenCharacter.toString());
                break;
            case 2:
                System.out.println("What is the new name of your character?");
                String newName = myScanner.nextLine();
                chosenCharacter.setName(newName);
                break;
            case 3:
                System.exit(0);
        }

        }

    private Character newCharacter(String characterType, String name){
        if(characterType.equals("Wizard")){
            return new Wizard(name);
        }else{
            return new Warrior(name);
        }
    }

}
