package dnd.game.board.cell;

import dnd.game.character.Character;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.LargePotion;
import dnd.game.loot.potion.SmallPotion;
import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Lightning;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

public class LootCell extends Cell {
    private Loot loot;

    public LootCell(Loot loot) {
        this.loot = loot;
    }

    @Override
    public void getDescription() {
        System.out.println(loot.getDescription());
    }

    @Override
    public void interact(Character character) {
        if (character.getType().equals("Warrior")) {
            if (loot instanceof Mace || loot instanceof Sword) {
                System.out.println("You got the loot!");
                character.setEquipment(loot);
                character.upgradeAttack();
            } else if (loot instanceof SmallPotion || loot instanceof LargePotion) {
                System.out.println("Heal up!");
                character.setEquipment(loot);
                character.heal();
            }else{
                System.out.println("You cannot equip this item");
            }
        } else if (character.getType().equals("Wizard")) {
            if (loot instanceof Lightning || loot instanceof Fireball) {
                System.out.println("You got the loot!");
                character.setEquipment(loot);
                character.upgradeAttack();
            } else if (loot instanceof SmallPotion || loot instanceof LargePotion) {
                System.out.println("Heal up!");
                character.setEquipment(loot);
                character.heal();
            }else{
                System.out.println("You cannot equip this item");
            }
        }
    }

}
