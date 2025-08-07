package dnd.game.loot.potion;

import dnd.game.loot.Loot;

public class Potion extends Loot {
    private String name;
    private int healEffect;
    private String description;
    private String inventoryDescription;
    private String type;

    public Potion(String name, int healEffect, String description, String inventoryDescription, String type){
        this.name = name;
        this.healEffect = healEffect;
        this.description = description;
        this.inventoryDescription = inventoryDescription;
        this.type = type;
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

    public void setHealEffect(int healEffect) {
        this.healEffect = healEffect;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInventoryDescription(String inventoryDescription) {
        this.inventoryDescription = inventoryDescription;
    }
}
