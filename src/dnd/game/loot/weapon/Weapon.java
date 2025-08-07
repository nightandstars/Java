package dnd.game.loot.weapon;

import dnd.game.loot.Loot;

public class Weapon extends Loot {
    private String name;
    private int attackEffect;
    private String description;
    private String inventoryDescription;
    private int specialAttackEffect;
    private String type;

    public Weapon(String name, int healEffect, String description, String inventoryDescription, int specialAttackEffect, String type){
        this.name = name;
        this.attackEffect = healEffect;
        this.description = description;
        this.inventoryDescription = inventoryDescription;
        this.specialAttackEffect = specialAttackEffect;
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

    public int getAttack() {
        return attackEffect;
    }

    public String getName() {
        return name;
    }

    public int getSpecialAttackEffect() {
        return specialAttackEffect;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttackEffect(int attackEffect) {
        this.attackEffect = attackEffect;
    }

    public void setSpecialAttackEffect(int specialAttackEffect) {
        this.specialAttackEffect = specialAttackEffect;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInventoryDescription(String inventoryDescription) {
        this.inventoryDescription = inventoryDescription;
    }
}
