package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.dice.Dice;
import dnd.game.enemy.Enemy;

import java.util.Random;

public class EnemyCell extends Cell implements Dice{
    private Enemy enemy;
    private String type = "enemy";

    public EnemyCell (Enemy enemy){
        this.enemy = enemy;
    }

    @Override
    public void getDescription() {
        System.out.println(enemy.getDescription());
    }

    @Override
    public void interact(Character character, int playerPosition, Board board) {
        //critical 1/20
        boolean isRunning = false;
        while ((character.getHealth() > 0 || enemy.getHealth() > 0) && !isRunning) {
            characterIsAttacking(character);
            if (enemy.getHealth() <= 0) {
                Menu.showMessage("You have defeated the enemy! Yay you!");
                break;
            }else{
                isRunning = enemyIsAttacking(character, isRunning, board);
            }
        }
    }

    private void characterIsAttacking(Character character) {
        int damage = character.getAttack();
        if (isBeatingArmorClass(character, "character")) {
            Menu.showMessage("You dealt " + damage + " damage");
            int newEnemyHealth = enemy.getHealth() - damage;
            enemy.setHealth(newEnemyHealth);
        }
        else {
            Menu.showMessage("You missed");
        }
    }

    private boolean enemyIsAttacking(Character character, boolean isRunning, Board board) {
        Menu menu = new Menu();
        int damage2 = enemy.getAttack();
        if (isBeatingArmorClass(character, "enemy")) {
            Menu.showMessage("The enemy dealt " + damage2 + " damage");
            int newCharacterHealth = character.getHealth() - damage2;
            character.setHealth(newCharacterHealth);
            if (character.getHealth() <= 0) {
                Menu.showMessage("Oh no! You died :(");
                menu.endOfGameChoice();
            }
        } else {
            Menu.showMessage("The enemy missed");
        }
            Menu.showMessage("You have " + character.getHealth() + " health");
            Menu.showMessage("The enemy has " + enemy.getHealth() + " health");
            int choice = menu.fightingMenu();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    int moveBack = rollD6();
                    board.goBackOnBoard(moveBack);
                    Menu.showMessage("You ran back " + moveBack + " cells");
                    isRunning = true;
                    break;
            }
            return isRunning;
        }

    private boolean isBeatingArmorClass(Character character, String whoIsAttacking){
        boolean ACBeaten = false;
        int characterAC = character.getArmorClass();
        int enemyAC = enemy.getArmorClass();
        int diceValue = rollD20();
        Menu.showMessage("Dice rolled to beat Armor Class: " + diceValue);
        switch (whoIsAttacking){
            case "character":
                if (diceValue > enemyAC){
                    ACBeaten = true;
                }
                break;
            case "enemy":
                if (diceValue > characterAC){
                    ACBeaten = true;
                }
                break;
        }
        return ACBeaten;
    }

    @Override
    public int rollD6() {
        Random diceRoll = new Random();
        int diceValue = diceRoll.nextInt(1,7);
        Menu.showMessage("You rolled a " + diceValue);
        return diceValue;
    }

    @Override
    public int rollD20() {
        Random diceRoll = new Random();
        return diceRoll.nextInt(1,21);
    }

    private int isCritical(int diceValue, int damage){
        // you're trying to figure out how to return damage *2 since damage and diceValue
        // come from two different methods, dicevalue is beatenAC, damage is charac/enemy attacking
        switch (diceValue){
            case 1:
                damage = 0;
                break;
            case 20:
                 damage *= 2;
                break;
        }
        return damage;
    }

}
