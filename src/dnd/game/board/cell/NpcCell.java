package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.loot.Loot;
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

    /**
     * Handles the buy/sell logic between npc and character
     * @param character the character that is being played
     * @param playerPosition the cell on which the character is
     * @param board the board that is being played on
     */
    @Override
    public void interact(Character character, int playerPosition, Board board) {
        Menu menu = new Menu();
        menu.notImplemented();
//        int choice = menu.buyOrSell();
//        switch (choice){
//            case 1:
//                npc.getInventory().showNpcInventory();
//                int index = menu.chooseItemToInteractWith("buy");
//                double coinsSpent = npc.getInventory().getItemInInventory(index).getBuyingPrice();
//                boolean purchaseSuccessful = character.getInventory().spendCoins((int)coinsSpent);
//                if (purchaseSuccessful){
//                    Loot loot = npc.getInventory().getItemInInventory(index);
//                    npc.getInventory().removeItemInInventory(index);
//                    npc.getInventory().addCoins((int)coinsSpent);
//                    character.getInventory().addInventory(loot);
//                };
//                break;
//            case 2:
//                character.getInventory().showPlayerInventory();
//                int indexSell = menu.chooseItemToInteractWith("sell");
//                Loot loot = character.getInventory().getItemInInventory(indexSell);
//                character.getInventory().removeItemInInventory(indexSell);
//                npc.getInventory().addInventory(loot);


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
