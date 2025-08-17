package dnd.game.character;

import dnd.game.Menu;
import dnd.game.loot.*;
import dnd.game.loot.spell.*;
import dnd.game.loot.weapon.*;
import dnd.game.loot.potion.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public int getLowestPrice(){
        return (int) Collections.min(items, Comparator.comparingDouble(Loot::getBuyingPrice)).getBuyingPrice();
    }

    public void replaceItemInInventory(int index, Loot loot){
        items.set(index, loot);
    }

    public Loot getItemInInventory(int index){
        return items.get(index);
    }

    public void removeItemInInventory(int index){
        items.remove(index);
    }

    public void showPlayerInventory() {
        Menu.showMessage("You have in your inventory: ");
        for(Loot loot : items){
            Menu.showMessage("["+ items.indexOf(loot) + "] " + loot.getInventoryDescription());
        }
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
        this.coins += value;
    }

    @Override
    public boolean spendCoins(int value) {
        boolean purchaseSuccessful = false;
        if(coins < value){
            Menu.showMessage("Looks like you don't have enough gold...");
            Menu.showMessage("You are missing " + (value - coins) + " gold");
        }else{
            coins -= value;
            purchaseSuccessful = true;
        }
        return purchaseSuccessful;
    }

    public void showCoins() {
        Menu.showMessage("You have " + coins + " gold");
    }

    public void showNpcInventory() {
        Menu.showMessage("Here are the items on sale: ");
        for(Loot loot : items){
            Menu.showMessage("[" + items.indexOf(loot) + "] Price: " + loot.getBuyingPrice() + " | " + loot.getInventoryDescription());
        }
    }

    public int getCoins(){
        return coins;
    }

    public int inventorySize(){
        return items.size();
    }

}
