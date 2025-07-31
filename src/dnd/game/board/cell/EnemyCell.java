package dnd.game.board.cell;

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

}
