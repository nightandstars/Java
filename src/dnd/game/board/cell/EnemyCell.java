package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.character.Character;
import dnd.game.enemy.Enemy;

public class EnemyCell extends Cell{
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
    public void interact(Character character, int playerPosition) {
        //implement AC, critical 1/20
        Menu menu = new Menu();
        System.out.println("Time to fight!");
        while(character.getHealth() > 0 || enemy.getHealth() > 0){

            int damage = character.getAttack();
            Menu.showMessage("You dealt " + damage + " points of damage");
            int newEnemyHealth = enemy.getHealth() - damage;
            enemy.setHealth(newEnemyHealth);

            if(enemy.getHealth() <= 0){
                Menu.showMessage("You have defeated the enemy! Yay you!");
                break;
            } else{
                int damage2 = enemy.getAttack();
                Menu.showMessage("The enemy dealt you " + damage + " damage");
                int newCharacterHealth = character.getHealth() - damage2;
                character.setHealth(newCharacterHealth);
                if(character.getHealth() <= 0){
                    Menu.showMessage("Oh no! You died :(");
                    menu.endOfGameChoice();
                }else{
                    Menu.showMessage("You have " + character.getHealth() + " health");
                    menu.fightingMenu();
                }
            }
        }
    }

}
