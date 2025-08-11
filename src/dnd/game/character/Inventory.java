package dnd.game.character;

import dnd.game.Menu;
import dnd.game.loot.*;
import dnd.game.loot.spell.*;
import dnd.game.loot.weapon.*;
import dnd.game.loot.potion.*;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements Coins {
    private List<Loot> items;
    private int coins;

    public Inventory(){
        this.items = new ArrayList<>();
    }

    public List<Loot> getInventory(){
        return items;
    }

    public void replaceItemInInventory(int index, Loot loot){
        items.set(index, loot);
    }

    public Loot getItemInInventory(int index){
        return items.get(index);
    }

    public void setInventory(List<Loot> inventory) {
        this.items = inventory;
    }

    public void showInventory() {
        for(Loot loot : items){
            Menu.showMessage("You have in your inventory: ");
            Menu.showMessage(loot.getInventoryDescription());
        }
    }

    public void addToInventory(Loot loot) {
        items.add(loot);
    }

    public int countPotionsInInventory(){
        return (int) items.stream().filter(loot -> loot instanceof Potion).count();
    }

    public void getPotionsInInventory(){
        Menu.showMessage("Here are the potions in your inventory:");
        for(Loot loot : items){
            if(loot instanceof Potion){
                Menu.showMessage("[" + items.indexOf(loot) + "] " + loot.getInventoryDescription());
            }
        }
    }

    public int countWeaponsInInventory(){
        return (int) items.stream().filter(loot -> loot instanceof Weapon).count();
    }

    public int countSpellsInInventory(){
        return (int) items.stream().filter(loot -> loot instanceof Spell).count();
    }

    public void getWeaponsInInventory(){
        Menu.showMessage("Here are the weapons in your inventory:");
        for(Loot loot : items){
            if(loot instanceof Weapon){
                Menu.showMessage("[" + items.indexOf(loot) + "] " + loot.getInventoryDescription());
            }
        }
    }

    public void getSpellsInInventory(){
        Menu.showMessage("Here are the spells in your inventory:");
        for(Loot loot : items){
            if(loot instanceof Spell){
                Menu.showMessage("[" + items.indexOf(loot) + "] " + loot.getInventoryDescription());
            }
        }
    }

    public void addInventory(Loot loot) {
        items.add(loot);
    }

    @Override
    public void addCoins(int value) {
        this.coins = value;
    }

    @Override
    public void spendCoins(int value) {
        coins -= value;
    }

    public int getCoins() {
        return coins;
    }

}
