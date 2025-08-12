package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.npc.Npc;

public class NpcCell extends Cell{

    private Npc npc;
    private final String type = "npc";

    public NpcCell(Npc npc){
        this.npc = npc;
    }

    @Override
    public void getDescription() {
        Menu.showMessage("What awaits you here?");
    }

    @Override
    public void interact(Character character, int playerPosition, Board board) {
        String npcType = npc.getType();
        switch (npcType){
            case "Merchant":

                break;
            case "Blacksmith":

                break;

            case "Arcanist":

                break;
        }
    }

    private void merchantInteract(){
        //menu to buy or sell
        //switch on choice
        //buy = list of all products in merchant's inventory -> name/effect/price/quantity
        //ask which item wants to buy by name
        //ask quantity
        //deduce coins from character
        //add coins to merchant
        //message you bought ... you now have xxx coins
        //menu anything else? -> buy again, sell, leave

        //sell = list of all products in character's inventory with sell price
        //ask item to sell by name
        //confirm sell
        //add it to merchant's inventory + remove coins
        //remove from player's inventory + add coins
        //message you sold... you now have xxx coins
        //menu anything else?

    }


}
