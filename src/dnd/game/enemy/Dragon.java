package dnd.game.enemy;

/**
 * Represents a Dragon in the game
 */
public class Dragon extends Enemy {
    public Dragon(){
        super("Dragon", 4, 15, "Dragon", 13);
    }

    @Override
    public String getDescription() {
        return "Woosh Woosh, a Dragon appeared over you, take cover! ";
    }
}
