package dnd.game.enemy;

/**
 * Represents a Dragon in the game
 */
public class EvilSpirit extends Enemy {
    public EvilSpirit(){
        super("EvilSpirit", 4, 15, "EvilSpirit", 13);
    }

    @Override
    public String getDescription() {
        return "Booh, an evil spirit is haunting this place";
    }
}
