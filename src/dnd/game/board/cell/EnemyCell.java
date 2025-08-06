package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.dice.Dice;
import dnd.game.enemy.Enemy;

import java.util.Random;

/**
 * Represents a cell containing en enemy to fight
 */
public class EnemyCell extends Cell implements Dice{
    private Enemy enemy;
    private String type = "enemy";

    public EnemyCell (Enemy enemy){
        this.enemy = enemy;
    }

    /**
     * Prints the description of the enemy present on the cell
     */
    @Override
    public void getDescription() {
        System.out.println(enemy.getDescription());
    }

    /**
     * Engages the fight, player hits first, checks if enemy is defeated, else enemy attacks
     * @param character the character that is being played
     * @param playerPosition the cell on which the character is
     * @param board the board that is being played on
     */
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

    /**
     * Checks if the hit lands and modifies the enemy's health
     * @param character the character being played and its stats
     */
    private void characterIsAttacking(Character character) {
        int damage = character.getAttack();
        int diceValue = rollD20();
        if (isBeatingArmorClass(character, "character", diceValue)) {
            damage = isCritical(diceValue, damage);
            Menu.showMessage("You dealt " + damage + " damage");
            int newEnemyHealth = enemy.getHealth() - damage;
            enemy.setHealth(newEnemyHealth);
        }
        else {
            Menu.showMessage("You missed");
        }
    }

    /**
     * Checks if the hit lands and modifies the character's health, checks if character is dead, else option to run or keep fighting
     * @param character the character being played and its stats
     * @param isRunning is the player running from the fight, false by default
     * @param board the board being played on
     * @return if the character runs from the fight or not
     */
    private boolean enemyIsAttacking(Character character, boolean isRunning, Board board) {
        Menu menu = new Menu();
        int damage = enemy.getAttack();
        int diceValue = rollD20();
        if (isBeatingArmorClass(character, "enemy", diceValue)) {
            damage = isCritical(diceValue, damage);
            Menu.showMessage("The enemy dealt " + damage + " damage");
            int newCharacterHealth = character.getHealth() - damage;
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

    /**
     * Checks the result of a random D20 against the enemy/character armor class, if dice is >= AC then the hit lands, otherwise it is missed
     * @param character character being played and its stats
     * @param whoIsAttacking enemy or character's turn to know which AC to check
     * @return is the AC beaten or not (ie is the hit successful)
     */
    private boolean isBeatingArmorClass(Character character, String whoIsAttacking, int diceValue){
        boolean ACBeaten = false;
        int characterAC = character.getArmorClass();
        int enemyAC = enemy.getArmorClass();
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

    /**
     * Rolls a random D6
     * @return the result of the throw
     */
    @Override
    public int rollD6() {
        Random diceRoll = new Random();
        int diceValue = diceRoll.nextInt(1,7);
        Menu.showMessage("You rolled a " + diceValue);
        return diceValue;
    }

    /**
     * Rolls a random D20
     * @return the result of the throw
     */
    @Override
    public int rollD20() {
        Random diceRoll = new Random();
        return diceRoll.nextInt(1,21);
    }

    /**
     * Checks if the hit is critical upon the D20 roll, critical success on a 20
     * @param diceValue the result of the D20
     * @param damage the damage being inflicted normally
     * @return damage unchanged or times 2 if dice value = 20
     */
    private int isCritical(int diceValue, int damage){
        if(diceValue == 20){
            damage *= 2;
        }
        return damage;
    }

}
