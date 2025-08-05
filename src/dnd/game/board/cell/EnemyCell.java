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
        //implement AC, critical 1/20
        Menu menu = new Menu();
        Menu.showMessage("Time to fight!");
        boolean isRunning = false;
        while((character.getHealth() > 0 || enemy.getHealth() > 0) && !isRunning){

            int damage = character.getAttack();
            Menu.showMessage("You dealt " + damage + " points of damage");
            int newEnemyHealth = enemy.getHealth() - damage;
            enemy.setHealth(newEnemyHealth);

            if(enemy.getHealth() <= 0){
                Menu.showMessage("You have defeated the enemy! Yay you!");
                break;
            } else{
                int damage2 = enemy.getAttack();
                Menu.showMessage("The enemy dealt " + damage + " damage");
                int newCharacterHealth = character.getHealth() - damage2;
                character.setHealth(newCharacterHealth);
                if(character.getHealth() <= 0){
                    Menu.showMessage("Oh no! You died :(");
                    menu.endOfGameChoice();
                }else{
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
                }
            }
        }
    }

    @Override
    public int rollD6() {
        Random diceRoll = new Random();
        int diceValue = diceRoll.nextInt(1,7);
        System.out.println("You rolled a " + diceValue);
        return diceValue;
    }

    @Override
    public int rollD20() {
        return 0;
    }
}
