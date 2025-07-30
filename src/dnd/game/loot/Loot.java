package dnd.game.loot;

import dnd.game.loot.potion.Potion;
import dnd.game.loot.spell.Spell;
import dnd.game.loot.weapon.Weapon;

public abstract class Loot {
    private Weapon weapon;
    private Spell spell;
    private Potion potion;

    public Potion getPotion() {
        return potion;
    }

    public Spell getSpell() {
        return spell;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
