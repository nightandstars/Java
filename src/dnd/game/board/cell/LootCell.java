package dnd.game.board.cell;

import dnd.game.Menu;
import dnd.game.board.Board;
import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.LargePotion;
import dnd.game.loot.potion.SmallPotion;
import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Invisibility;
import dnd.game.loot.spell.Lightning;
import dnd.game.loot.weapon.Bow;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

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
        if (character instanceof Warrior) {
            if (loot instanceof Mace || loot instanceof Sword || loot instanceof Bow) {
                System.out.println("You got the loot!");
                character.setEquipment(loot);
                character.upgradeAttack();
                board.replaceCell(playerPosition);
            } else if (loot instanceof SmallPotion || loot instanceof LargePotion) {
                System.out.println("Heal up!");
                character.setEquipment(loot);
                character.heal();
                board.replaceCell(playerPosition);
            }else{
                Menu.showMessage("You cannot equip this item");
            }
        } else if (character instanceof Wizard) {
            if (loot instanceof Lightning || loot instanceof Fireball || loot instanceof Invisibility) {
                System.out.println("You got the loot!");
                character.setEquipment(loot);
                character.upgradeAttack();
                board.replaceCell(playerPosition);
            } else if (loot instanceof SmallPotion || loot instanceof LargePotion) {
                System.out.println("Heal up!");
                character.setEquipment(loot);
                character.heal();
                board.replaceCell(playerPosition);
            }else{
                Menu.showMessage("You cannot equip this item");
            }
        }
    }

}
