package dnd.game.npc;

import dnd.game.loot.weapon.Bow;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

import java.util.Random;

public class Blacksmith extends Npc {

    public Blacksmith(){
        super("Blacksmith", "Blacksmith");
        fillInventoryRandom();
    }

    @Override
    public String getDescription() {
        return "Tink, tink, got some weapons to repair?";
    }

    @Override
    public void fillInventoryRandom() {
        Random random = new Random();
        int maxSize = 6;
        while(inventory.getInventory().size() < maxSize){
            int lootType = random.nextInt(3);
            switch (lootType){
                case 0:
                    inventory.addInventory(new Bow());
                    break;
                case 1:
                    inventory.addInventory(new Mace());
                    break;
                case 2:
                    inventory.addInventory(new Sword());
                    break;
            }
        }
    }


}
