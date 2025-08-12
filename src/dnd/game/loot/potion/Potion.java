package dnd.game.loot.potion;

import dnd.game.loot.Loot;

import static java.lang.Math.floor;
import static java.lang.Math.round;

public class Potion extends Loot {
    private String name;
    private int healEffect;
    private String description;
    private String inventoryDescription;
    private String type;
    private double buyingPrice;
    private double sellingPrice;

    public Potion(String name, int healEffect, String description, String inventoryDescription, String type){
        this.name = name;
        this.healEffect = healEffect;
        this.description = description;
        this.inventoryDescription = inventoryDescription;
        this.type = type;
        determineBuySellPrice();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getInventoryDescription() {
        return inventoryDescription;
    }

    public int getHeal() {
        return healEffect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void determineBuySellPrice(){
        final int BASE_PER_POINT = 3;
        final double MULTIPLIER = 1.6;
        final double SELL_RATIO = 0.5;

        int heal = getHeal();

        double baseValue = heal * BASE_PER_POINT;

        buyingPrice = round(baseValue);
        sellingPrice = floor(buyingPrice * SELL_RATIO);
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }
}
