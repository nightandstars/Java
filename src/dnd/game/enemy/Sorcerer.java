package dnd.game.enemy;

/**
 * Represents a sorcerer in the game
 */
public class Sorcerer extends Enemy {
    public Sorcerer(){
        super("Sorcerer", 2,9, "Sorcerer", 11);
    }

    @Override
    public String getDescription() {
        return "Bipity Bopity, here's the Sorcerer";
    }
}
