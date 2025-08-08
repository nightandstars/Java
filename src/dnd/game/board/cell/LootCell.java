package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.Potion;
import dnd.game.loot.spell.Spell;
import dnd.game.loot.weapon.Weapon;

/**
 * Represents a cell containing loot on the board
 */
public class LootCell extends Cell {
    private Loot loot;
    private String type = "loot";

    public LootCell(Loot loot) {
        this.loot = loot;
    }

    /**
     * Gets the description of the loot present on the cell
     */
    @Override
    public void getDescription() {
        Menu.showMessage(loot.getDescription());
    }

    /**
     * Checks the loot present on the cell against the character's type to meet certain conditions regarding weapons and spells, if the loot is a potion the character heals
     * @param character the character that is being played
     * @param playerPosition the cell on which the character is
     * @param board the board that is being played on
     */
    @Override
    public void interact(Character character, int playerPosition, Board board) {
        Menu menu = new Menu();
        boolean isPickedUp = false;
        if (character instanceof Warrior) {
            if (loot instanceof Weapon) {
                if (character.countWeaponsInInventory() < 2) {
                    character.addInventory(loot);
                    Menu.showMessage("The weapon has been added to your inventory");
                    isPickedUp = true;
                } else {
                    character.getWeaponsInInventory();
                    int choice = menu.choosePickupOrDrop();
                    if (choice == 1) {
                        int index = menu.itemToReplace();
                        character.replaceItemInInventory(index, loot);
                        isPickedUp = true;
                    }
                }if(isPickedUp){
                    board.replaceCell(playerPosition);
                }
            }
        else if (loot instanceof Potion) {
                System.out.println("Heal up!");
                isPickedUp = character.heal((Potion)loot);
                if(isPickedUp){
                    board.replaceCell(playerPosition);
                }
            }else{
                Menu.showMessage("You cannot equip this item");
            }
        }

        else if (character instanceof Wizard) {
            if (loot instanceof Spell) {
                if (character.countSpellsInInventory() < 2) {
                    character.addInventory(loot);
                    Menu.showMessage("The spell has been added to your inventory");
                    isPickedUp = true;
                } else {
                    character.getSpellsInInventory();
                    int choice = menu.choosePickupOrDrop();
                    if (choice == 1) {
                        int index = menu.itemToReplace();
                        character.replaceItemInInventory(index, loot);
                        isPickedUp = true;
                    }
                }if(isPickedUp){
                    board.replaceCell(playerPosition);
                }
            } else if (loot instanceof Potion) {
                System.out.println("Heal up!");
                isPickedUp = character.heal((Potion)loot);
                if(isPickedUp){
                    board.replaceCell(playerPosition);
                }
            }else{
                Menu.showMessage("You cannot equip this item");
            }
        }
    }

}
