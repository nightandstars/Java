package dnd.game.npc;

import dnd.game.loot.potion.LargePotion;
import dnd.game.loot.potion.SmallPotion;


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

}
