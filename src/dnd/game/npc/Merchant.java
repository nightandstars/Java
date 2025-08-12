package dnd.game.npc;

import dnd.game.character.Inventory;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.LargePotion;
import dnd.game.loot.potion.SmallPotion;
import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Invisibility;
import dnd.game.loot.spell.Lightning;
import dnd.game.loot.weapon.Bow;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

import java.util.Random;

public class Merchant extends Npc{

    public Merchant(){
        super("Merchant", "Merchant");
        fillInventory(2, new SmallPotion());
        fillInventory(2, new LargePotion());
        fillInventoryRandom();
        //add backpack

    }

    @Override
    public String getDescription() {
        return "You have encountered a Merchant, feel free to do some shopping";
    }

    public void interact(){
        //if player buys weapon can't equip say sorry can't buy this
    }


}
