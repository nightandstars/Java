package dnd.game.loot.spell;

import dnd.game.loot.Loot;

public class Spell extends Loot {
    private String name;
    private int attackEffect;
    private String description;
    private String inventoryDescription;
    private int specialAttackEffect;
    private String type;

    public Spell(String name, int attackEffect, String description, String inventoryDescription, int specialAttackEffect, String type){
        this.name = name;
        this.attackEffect = attackEffect;
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
