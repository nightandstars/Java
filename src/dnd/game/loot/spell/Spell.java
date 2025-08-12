package dnd.game.loot.spell;

import dnd.game.loot.Loot;

import static java.lang.Math.floor;
import static java.lang.Math.round;

public class Spell extends Loot {
    private String name;
    private int attackEffect;
    private String description;
    private String inventoryDescription;
    private int specialAttackEffect;
    private String type;
    private double buyingPrice;
    private double sellingPrice;

    public Spell(String name, int attackEffect, String description, String inventoryDescription, int specialAttackEffect, String type){
        this.name = name;
        this.attackEffect = attackEffect;
        this.description = description;
        this.inventoryDescription = inventoryDescription;
        this.specialAttackEffect = specialAttackEffect;
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

    public int getAttackEffect() {
        return attackEffect;
    }

    public int getSpecialAttackEffect() {
        return specialAttackEffect;
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

        int attack = getAttackEffect();
        int specialAttack = getSpecialAttackEffect();

        double baseValue = attack * BASE_PER_POINT;

        if(specialAttack > 0){
            baseValue *= MULTIPLIER;
        }
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
