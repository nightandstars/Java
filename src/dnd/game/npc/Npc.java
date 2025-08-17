package dnd.game.npc;

import dnd.game.character.Inventory;
import dnd.game.loot.Loot;
import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Invisibility;
import dnd.game.loot.spell.Lightning;
import dnd.game.loot.weapon.Bow;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

import java.util.Random;

public class Npc {
    private String name;
    private String type;
    protected Inventory inventory;

    /**
     * Creates a new specific npc with an inventory and 100 gold to start with
     * @param name of the npc
     * @param type of the npc
     */
    public Npc(String name, String type){
        this.name = name;
        this.type = type;
        this.inventory = new Inventory();
        inventory.addCoins(100);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return "An NPC is waiting for you!";
    };

    public String getType() {
        return type;
    }

    public void fillInventory(int numberOfItems, Loot loot){
        for(int i = 0; i < numberOfItems; i++){
            inventory.addInventory(loot);
        }
    }

    /**
     * Default merchant inventory items, changes in specific npcs (ex blacksmith = only weapons)
     */
    public void fillInventoryRandom(){
        Random random = new Random();
        int maxSize = 10;
        while(inventory.getInventory().size() < maxSize){
            int lootType = random.nextInt(6);
            switch (lootType){
                case 0:
                    inventory.addInventory(new Invisibility());
                    break;
                case 1:
                    inventory.addInventory(new Bow());
                    break;
                case 2:
                    inventory.addInventory(new Fireball());
                    break;
                case 3:
                    inventory.addInventory(new Lightning());
                    break;
                case 4:
                    inventory.addInventory(new Sword());
                    break;
                case 5:
                    inventory.addInventory(new Mace());
                    break;
            }
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }


}
