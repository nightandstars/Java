package dnd.game.board.cell;

import dnd.game.character.Character;
import dnd.game.enemy.Enemy;

public class EnemyCell extends Cell{
    private Enemy enemy;

    public EnemyCell (Enemy enemy){
        this.enemy = enemy;
    }

    @Override
    public void getDescription() {
        System.out.println(enemy.getDescription());
    }

    @Override
    public void interact(Character character) {
        System.out.println("Time to fight!");
//        int damage = character.getAttack();
//        System.out.println("You dealt " + damage + " points of damage");
//        int newEnemyHealth = enemy.getHealth() - damage;
//        enemy.setHealth(newEnemyHealth);
//        int damage2 = enemy.getAttack();
//        System.out.println("The enemy hurt you for " + damage + " points");
//        int newCharacterHealth = character.getHealth() - damage2;
//        character.setHealth(newCharacterHealth);
    }
}
