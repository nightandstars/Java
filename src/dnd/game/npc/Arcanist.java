package dnd.game.npc;

import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Invisibility;
import dnd.game.loot.spell.Lightning;

import java.util.Random;

public class Arcanist extends Npc{

    public Arcanist(){
        super("Arcanist", "Arcanist");
        fillInventoryRandom();
    }

    @Override
    public String getDescription() {
        return "This place is filled with Magic, welcome to the Arcanist's shop";
    }

    @Override
    public void fillInventoryRandom() {
        Random random = new Random();
        int maxSize = 6;
        while(inventory.getInventory().size() < maxSize){
            int lootType = random.nextInt(3);
            switch (lootType){
                case 0:
                    inventory.addInventory(new Invisibility());
                    break;
                case 1:
                    inventory.addInventory(new Fireball());
                    break;
                case 2:
                    inventory.addInventory(new Lightning());
                    break;
            }
        }
    }


}
