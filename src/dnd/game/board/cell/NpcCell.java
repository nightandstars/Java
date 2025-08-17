package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.character.Inventory;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;
import dnd.game.loot.Loot;
import dnd.game.loot.spell.Spell;
import dnd.game.loot.weapon.Weapon;
import dnd.game.npc.Npc;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NpcCell extends Cell{

    private Npc npc;
    private final String type = "npc";

    public NpcCell(Npc npc){
        this.npc = npc;
    }

    @Override
    public void getDescription() {
        Menu.showMessage(npc.getDescription());
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
        Inventory characterInventory = character.getInventory();
        Inventory npcInventory = npc.getInventory();
        boolean playerExit = false;

        while(!playerExit){
            characterInventory.showCoins();
            int choice = menu.buyOrSell();
            switch (choice) {
                case 1:
                    npcInventory.showNpcInventory();
                    int lowestPrice = npcInventory.getLowestPrice();
                    if(characterInventory.getCoins() < lowestPrice){
                        Menu.showMessage("Looks like you can't buy anything, come back later");
                    }else{
                        int index = menu.chooseItemToInteractWith("buy");
                        double coinsSpent = npcInventory.getItemInInventory(index).getBuyingPrice();
                        boolean purchaseSuccessful = characterInventory.spendCoins((int) coinsSpent);
                        if (purchaseSuccessful) {
                            Loot loot = npcInventory.getItemInInventory(index);
                            if((character instanceof Warrior && loot instanceof Spell) || (character instanceof Wizard && loot instanceof Weapon)){
                                Menu.showMessage("You cannot buy this category of item");
                                characterInventory.addCoins((int)coinsSpent);
                            }else{
                                npcInventory.removeItemInInventory(index);
                                npcInventory.addCoins((int) coinsSpent);
                                characterInventory.addInventory(loot);
                                Menu.showMessage("Your purchase was successful");
                            }
                        }
                    }
                    break;
                case 2:
                    int inventorySize = characterInventory.inventorySize();
                    if(inventorySize == 0){
                        Menu.showMessage("You don't have anything to sell");
                    }else{
                        characterInventory.showPlayerInventory();
                        int indexSell = menu.chooseItemToInteractWith("sell");
                        if (indexSell == -1){
                            break;
                        }else{
                            Loot loot = characterInventory.getItemInInventory(indexSell);
                            npcInventory.addInventory(loot);
                            double coinsSpentSell = characterInventory.getItemInInventory(indexSell).getSellingPrice();
                            characterInventory.addCoins((int)coinsSpentSell);
                            Menu.showMessage("You now have " + characterInventory.getCoins() + " gold");
                            npcInventory.spendCoins((int) coinsSpentSell);
                            characterInventory.removeItemInInventory(indexSell);
                        }
                    }
                    break;
                default:
                    playerExit = true;
                    break;
            }
        }
    }


}
