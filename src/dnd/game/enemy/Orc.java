package dnd.game.enemy;

/**
 * Represents a Dragon in the game
 */
public class Orc extends Enemy {
    public Orc(){
        super("Orc", 6, 10, "Orc", 12);
    }

    @Override
    public String getDescription() {
        return "Hide! Here's an Orc";
    }
}
