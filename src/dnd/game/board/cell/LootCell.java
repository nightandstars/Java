package dnd.game.board.cell;

import dnd.game.loot.Loot;

public class LootCell extends Cell{
    private Loot loot;

    public LootCell (Loot loot){
        this.loot = loot;
    }

    @Override
    public void getDescription() {
        System.out.println(loot.getDescription());
    }
}
